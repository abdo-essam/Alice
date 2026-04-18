package com.ae.alice.data

import com.ae.alice.domain.entity.User
import com.ae.alice.domain.repository.AuthRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FirebaseAuthRepository : AuthRepository {

    private val auth = Firebase.auth

    override suspend fun login(email: String, password: String): User {
        val result = auth.signInWithEmailAndPassword(email, password)
        val firebaseUser = result.user
            ?: throw IllegalStateException("Login failed: no user returned")
        return User(
            id = firebaseUser.uid,
            email = firebaseUser.email ?: email,
            displayName = firebaseUser.displayName
        )
    }

    override suspend fun loginWithGoogle(idToken: String, accessToken: String?): User {
        val credential = dev.gitlive.firebase.auth.GoogleAuthProvider.credential(idToken, accessToken)
        val result = auth.signInWithCredential(credential)
        val firebaseUser = result.user
            ?: throw IllegalStateException("Google Login failed: no user returned")
        return User(
            id = firebaseUser.uid,
            email = firebaseUser.email ?: "",
            displayName = firebaseUser.displayName
        )
    }

    override suspend fun register(email: String, password: String, displayName: String): User {
        val result = auth.createUserWithEmailAndPassword(email, password)
        val firebaseUser = result.user
            ?: throw IllegalStateException("Registration failed: no user returned")
        firebaseUser.updateProfile(displayName = displayName)
        return User(
            id = firebaseUser.uid,
            email = firebaseUser.email ?: email,
            displayName = displayName
        )
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override suspend fun getCurrentUser(): User? {
        val firebaseUser = auth.currentUser ?: return null
        return User(
            id = firebaseUser.uid,
            email = firebaseUser.email ?: "",
            displayName = firebaseUser.displayName
        )
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return auth.authStateChanged.map { it != null }
    }
}
