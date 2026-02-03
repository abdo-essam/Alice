package com.ae.alice.core.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.ae.alice.core.common.result.ErrorState
import com.ae.alice.core.common.result.Result
import com.ae.alice.core.common.result.toErrorState

/**
 * Base ViewModel implementing MVI (Model-View-Intent) pattern.
 * Provides state management, intent handling, and side effects.
 *
 * @param S The UI state type
 * @param I The intent/action type  
 * @param E The one-time effect type
 */
abstract class BaseViewModel<S : UiState, I : UiIntent, E : UiEffect>(
    initialState: S
) : ViewModel() {
    
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()
    
    private val _effect = Channel<E>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()
    
    protected val currentState: S
        get() = _state.value
    
    /**
     * Process an intent/action from the UI
     */
    fun processIntent(intent: I) {
        handleIntent(intent)
    }
    
    /**
     * Override this to handle specific intents
     */
    protected abstract fun handleIntent(intent: I)
    
    /**
     * Update the UI state
     */
    protected fun updateState(reducer: S.() -> S) {
        _state.update { it.reducer() }
    }
    
    /**
     * Emit a one-time side effect
     */
    protected fun emitEffect(effect: E) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
    
    /**
     * Execute a suspending operation with loading state management.
     * Automatically handles loading state, success, and error states.
     */
    protected fun <T> tryExecute(
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        onStart: (() -> Unit)? = null,
        onSuccess: (T) -> Unit,
        onError: (ErrorState) -> Unit,
        onCompleted: (() -> Unit)? = null,
        execute: suspend () -> Result<T>
    ): Job {
        return viewModelScope.launch(dispatcher) {
            onStart?.invoke()
            
            when (val result = execute()) {
                is Result.Success -> onSuccess(result.data)
                is Result.Error -> onError(result.error)
                is Result.Loading -> { /* handled by onStart */ }
            }
            
            onCompleted?.invoke()
        }
    }
    
    /**
     * Execute a suspending operation that returns a direct value (not Result wrapped)
     */
    protected fun <T> tryExecuteDirect(
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        onStart: (() -> Unit)? = null,
        onSuccess: (T) -> Unit,
        onError: (ErrorState) -> Unit,
        onCompleted: (() -> Unit)? = null,
        execute: suspend () -> T
    ): Job {
        return viewModelScope.launch(dispatcher) {
            onStart?.invoke()
            
            try {
                val result = execute()
                onSuccess(result)
            } catch (e: Exception) {
                onError(mapToErrorState(e))
            } finally {
                onCompleted?.invoke()
            }
        }
    }
    
    /**
     * Override this to provide custom exception to ErrorState mapping
     */
    protected open fun mapToErrorState(exception: Throwable): ErrorState {
        return exception.toErrorState()
    }
    
    /**
     * Launch a coroutine in viewModelScope
     */
    protected fun launch(
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(dispatcher, block = block)
    }
}

/**
 * Marker interface for UI state
 */
interface UiState

/**
 * Marker interface for UI intents/actions
 */
interface UiIntent

/**
 * Marker interface for one-time UI effects
 */
interface UiEffect
