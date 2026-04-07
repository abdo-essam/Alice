package com.ae.alice.network.error

sealed interface ErrorState {
    val message: String?
    
    sealed interface Auth : ErrorState {
        data class Unauthorized(override val message: String?) : Auth
        data class Forbidden(override val message: String?) : Auth
    }
    
    data class NotFound(override val message: String?) : ErrorState
    data class Validation(override val message: String?) : ErrorState
    
    sealed interface Network : ErrorState {
        data class ClientError(val code: Int, override val message: String?) : Network
        data class ServerError(val code: Int, override val message: String?) : Network
        data class Timeout(override val message: String?) : Network
        data class NoConnection(override val message: String?) : Network
    }
    
    data class Unknown(override val message: String? = null, val throwable: Throwable? = null) : ErrorState
}
