package com.ae.alice.core.common.result

/**
 * Represents various error states that can occur in the application.
 * Centralized error handling following the Single Responsibility Principle.
 */
sealed class ErrorState(
    open val message: String? = null,
    open val throwable: Throwable? = null
) {
    
    /**
     * Network-related errors
     */
    sealed class Network(
        override val message: String? = null,
        override val throwable: Throwable? = null
    ) : ErrorState(message, throwable) {
        
        data class NoConnection(
            override val message: String? = null
        ) : Network(message)
        
        data class Timeout(
            override val message: String? = null
        ) : Network(message)
        
        data class ServerError(
            val code: Int,
            override val message: String? = null
        ) : Network(message)
        
        data class ClientError(
            val code: Int,
            override val message: String? = null
        ) : Network(message)
    }
    
    /**
     * Data validation errors
     */
    data class Validation(
        override val message: String? = null,
        val field: String? = null
    ) : ErrorState(message)
    
    /**
     * Local storage errors
     */
    data class Storage(
        override val message: String? = null,
        override val throwable: Throwable? = null
    ) : ErrorState(message, throwable)
    
    /**
     * Authentication/Authorization errors
     */
    sealed class Auth(
        override val message: String? = null
    ) : ErrorState(message) {
        
        data class Unauthorized(
            override val message: String? = null
        ) : Auth(message)
        
        data class Forbidden(
            override val message: String? = null
        ) : Auth(message)
        
        data class TokenExpired(
            override val message: String? = null
        ) : Auth(message)
    }
    
    /**
     * Business logic errors
     */
    data class Business(
        override val message: String? = null,
        val code: String? = null
    ) : ErrorState(message)
    
    /**
     * Not found errors
     */
    data class NotFound(
        override val message: String? = null,
        val resourceType: String? = null
    ) : ErrorState(message)
    
    /**
     * Empty data errors
     */
    data class Empty(
        override val message: String? = null
    ) : ErrorState(message)
    
    /**
     * Unknown/Unexpected errors
     */
    data class Unknown(
        override val message: String? = null,
        override val throwable: Throwable? = null
    ) : ErrorState(message, throwable)
}

/**
 * Extension function to convert Throwable to ErrorState
 */
fun Throwable.toErrorState(): ErrorState {
    return when {
        message?.contains("timeout", ignoreCase = true) == true -> 
            ErrorState.Network.Timeout(message)
        message?.contains("connection", ignoreCase = true) == true || 
        message?.contains("network", ignoreCase = true) == true -> 
            ErrorState.Network.NoConnection(message)
        else -> ErrorState.Unknown(message = message, throwable = this)
    }
}
