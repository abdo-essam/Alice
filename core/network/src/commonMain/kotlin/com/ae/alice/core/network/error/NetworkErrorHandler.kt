package com.ae.alice.core.network.error

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import com.ae.alice.core.common.result.ErrorState

/**
 * Maps network exceptions to ErrorState.
 * Handles Ktor-specific exceptions and HTTP status codes.
 */
object NetworkErrorHandler {
    
    /**
     * Maps a Throwable to an appropriate ErrorState
     */
    fun mapError(throwable: Throwable): ErrorState {
        return when (throwable) {
            is ClientRequestException -> mapClientError(throwable)
            is ServerResponseException -> mapServerError(throwable)
            else -> mapGenericError(throwable)
        }
    }
    
    /**
     * Maps HTTP 4xx client errors
     */
    private fun mapClientError(exception: ClientRequestException): ErrorState {
        val statusCode = exception.response.status.value
        return when (statusCode) {
            HttpStatusCode.Unauthorized.value -> 
                ErrorState.Auth.Unauthorized(exception.message)
            HttpStatusCode.Forbidden.value -> 
                ErrorState.Auth.Forbidden(exception.message)
            HttpStatusCode.NotFound.value -> 
                ErrorState.NotFound(exception.message)
            HttpStatusCode.BadRequest.value,
            HttpStatusCode.UnprocessableEntity.value -> 
                ErrorState.Validation(exception.message)
            else -> 
                ErrorState.Network.ClientError(statusCode, exception.message)
        }
    }
    
    /**
     * Maps HTTP 5xx server errors
     */
    private fun mapServerError(exception: ServerResponseException): ErrorState {
        val statusCode = exception.response.status.value
        return ErrorState.Network.ServerError(statusCode, exception.message)
    }
    
    /**
     * Maps generic/unknown errors
     */
    private fun mapGenericError(throwable: Throwable): ErrorState {
        val message = throwable.message ?: "Unknown error"
        
        return when {
            message.contains("timeout", ignoreCase = true) ||
            throwable::class.simpleName?.contains("Timeout") == true ->
                ErrorState.Network.Timeout(message)
            
            message.contains("connection", ignoreCase = true) ||
            message.contains("network", ignoreCase = true) ||
            message.contains("UnknownHost", ignoreCase = true) ||
            throwable::class.simpleName?.contains("UnknownHost") == true ||
            throwable::class.simpleName?.contains("Connect") == true ->
                ErrorState.Network.NoConnection(message)
            
            else -> ErrorState.Unknown(message = message, throwable = throwable)
        }
    }
}
