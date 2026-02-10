package com.ae.alice.common.result

/**
 * A sealed class representing the result of an operation.
 * Used for error handling and success tracking across the app.
 */
sealed class Result<out T> {
    
    /**
     * Represents a successful operation with data
     */
    data class Success<T>(val data: T) : Result<T>()
    
    /**
     * Represents a failed operation with an error
     */
    data class Error(val error: ErrorState) : Result<Nothing>()
    
    /**
     * Represents a loading state
     */
    data object Loading : Result<Nothing>()
    
    /**
     * Returns true if the result is a success
     */
    val isSuccess: Boolean
        get() = this is Success
    
    /**
     * Returns true if the result is an error
     */
    val isError: Boolean
        get() = this is Error
    
    /**
     * Returns true if the result is loading
     */
    val isLoading: Boolean
        get() = this is Loading
    
    /**
     * Returns the data if success, null otherwise
     */
    fun getOrNull(): T? = when (this) {
        is Success -> data
        else -> null
    }
    
    /**
     * Returns the data if success, or the default value
     */
    fun getOrDefault(default: @UnsafeVariance T): T = when (this) {
        is Success -> data
        else -> default
    }
    
    /**
     * Returns the error if error, null otherwise
     */
    fun errorOrNull(): ErrorState? = when (this) {
        is Error -> error
        else -> null
    }
    
    /**
     * Maps the success data to a new type
     */
    inline fun <R> map(transform: (T) -> R): Result<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> this
        is Loading -> Loading
    }
    
    /**
     * Flat maps the success data to a new Result
     */
    inline fun <R> flatMap(transform: (T) -> Result<R>): Result<R> = when (this) {
        is Success -> transform(data)
        is Error -> this
        is Loading -> Loading
    }
    
    /**
     * Executes an action if the result is successful
     */
    inline fun onSuccess(action: (T) -> Unit): Result<T> {
        if (this is Success) action(data)
        return this
    }
    
    /**
     * Executes an action if the result is an error
     */
    inline fun onError(action: (ErrorState) -> Unit): Result<T> {
        if (this is Error) action(error)
        return this
    }
    
    /**
     * Executes an action if the result is loading
     */
    inline fun onLoading(action: () -> Unit): Result<T> {
        if (this is Loading) action()
        return this
    }
}

/**
 * Extension function to convert nullable value to Result
 */
fun <T> T?.toResult(errorIfNull: ErrorState = ErrorState.Unknown()): Result<T> =
    if (this != null) Result.Success(this) else Result.Error(errorIfNull)

/**
 * Extension function to convert a throwable to Result.Error
 */
fun Throwable.toErrorResult(): Result<Nothing> = Result.Error(this.toErrorState())
