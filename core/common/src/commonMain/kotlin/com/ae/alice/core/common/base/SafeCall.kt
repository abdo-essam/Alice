package com.ae.alice.core.common.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.ae.alice.core.common.result.ErrorState
import com.ae.alice.core.common.result.Result

/**
 * Safe API call wrapper for repositories.
 * Wraps network/database calls in try-catch and returns Result.
 * 
 * Usage:
 * ```
 * suspend fun getBrands(): Result<List<Brand>> = safeApiCall {
 *     apiService.getBrands()
 * }
 * ```
 */
suspend inline fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    crossinline apiCall: suspend () -> T
): Result<T> {
    return withContext(dispatcher) {
        try {
            Result.Success(apiCall())
        } catch (e: Exception) {
            Result.Error(mapException(e))
        }
    }
}

/**
 * Safe call wrapper for local database operations
 */
suspend inline fun <T> safeDbCall(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    crossinline dbCall: suspend () -> T
): Result<T> {
    return withContext(dispatcher) {
        try {
            Result.Success(dbCall())
        } catch (e: Exception) {
            Result.Error(ErrorState.Storage(message = e.message, throwable = e))
        }
    }
}

/**
 * Safe call wrapper that doesn't switch dispatcher (for already suspended operations)
 */
suspend inline fun <T> safeCatching(
    crossinline block: suspend () -> T
): Result<T> {
    return try {
        Result.Success(block())
    } catch (e: Exception) {
        Result.Error(mapException(e))
    }
}

/**
 * Maps exceptions to appropriate ErrorState
 */
fun mapException(e: Exception): ErrorState {
    return when {
        // Network exceptions
        e.message?.contains("timeout", ignoreCase = true) == true ||
        e::class.simpleName?.contains("Timeout", ignoreCase = true) == true ->
            ErrorState.Network.Timeout(e.message)
        
        e.message?.contains("connection", ignoreCase = true) == true ||
        e.message?.contains("network", ignoreCase = true) == true ||
        e::class.simpleName?.contains("UnknownHost", ignoreCase = true) == true ||
        e::class.simpleName?.contains("Connect", ignoreCase = true) == true ->
            ErrorState.Network.NoConnection(e.message)
        
        // HTTP status code based errors would be handled in network module
        // This is a fallback for general exceptions
        
        else -> ErrorState.Unknown(message = e.message, throwable = e)
    }
}

/**
 * Extension function to execute multiple operations and combine results
 */
suspend inline fun <T, R> Result<T>.andThen(
    crossinline transform: suspend (T) -> Result<R>
): Result<R> {
    return when (this) {
        is Result.Success -> transform(data)
        is Result.Error -> this
        is Result.Loading -> Result.Loading
    }
}

/**
 * Retry wrapper for flaky operations
 */
suspend inline fun <T> retryWithBackoff(
    times: Int = 3,
    initialDelayMs: Long = 100,
    factor: Double = 2.0,
    crossinline block: suspend () -> Result<T>
): Result<T> {
    var currentDelay = initialDelayMs
    var lastError: ErrorState? = null
    
    repeat(times) { attempt ->
        when (val result = block()) {
            is Result.Success -> return result
            is Result.Error -> {
                lastError = result.error
                if (attempt < times - 1) {
                    kotlinx.coroutines.delay(currentDelay)
                    currentDelay = (currentDelay * factor).toLong()
                }
            }
            is Result.Loading -> { /* continue */ }
        }
    }
    
    return Result.Error(lastError ?: ErrorState.Unknown())
}
