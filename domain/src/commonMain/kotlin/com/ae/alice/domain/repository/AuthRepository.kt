package com.ae.alice.domain.repository

import com.ae.alice.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): User
    suspend fun register(email: String, password: String, displayName: String): User
    suspend fun logout()
    suspend fun getCurrentUser(): User?
    fun isLoggedIn(): Flow<Boolean>
}
