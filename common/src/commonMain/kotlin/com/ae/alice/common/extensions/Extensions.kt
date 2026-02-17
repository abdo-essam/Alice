package com.ae.alice.common.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import com.ae.alice.common.result.Result
import com.ae.alice.common.result.toErrorState

/**
 * Common Kotlin/Flow extensions used across the application.
 */

/**
 * Converts a Flow to emit Result objects, handling errors
 */
fun <T> Flow<T>.asResult(): Flow<Result<T>> = this
    .map<T, Result<T>> { Result.Success(it) }
    .onStart { emit(Result.Loading) }
    .catch { emit(Result.Error(it.toErrorState())) }

/**
 * Maps a Flow of Result to a new type
 */
fun <T, R> Flow<Result<T>>.mapResult(transform: (T) -> R): Flow<Result<R>> = 
    this.map { result -> result.map(transform) }

/**
 * String extensions
 */

/**
 * Returns true if the string is not null and not blank
 */
fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()

/**
 * Capitalizes the first letter of each word
 */
fun String.capitalizeWords(): String = 
    split(" ").joinToString(" ") { word ->
        word.lowercase().replaceFirstChar { 
            if (it.isLowerCase()) it.titlecase() else it.toString() 
        }
    }

/**
 * Truncates the string to the specified length with ellipsis
 */
fun String.truncate(maxLength: Int, ellipsis: String = "..."): String {
    return if (length <= maxLength) this
    else take(maxLength - ellipsis.length) + ellipsis
}

/**
 * Collection extensions
 */

/**
 * Returns the list or empty list if null
 */
fun <T> List<T>?.orEmpty(): List<T> = this ?: emptyList()

/**
 * Updates an item in the list matching the predicate
 */
inline fun <T> List<T>.updateItem(predicate: (T) -> Boolean, transform: (T) -> T): List<T> =
    map { if (predicate(it)) transform(it) else it }

/**
 * Removes the first item matching the predicate
 */
inline fun <T> List<T>.removeFirst(predicate: (T) -> Boolean): List<T> {
    val index = indexOfFirst(predicate)
    return if (index >= 0) filterIndexed { i, _ -> i != index } else this
}

/**
 * Toggles an item in the list (adds if not present, removes if present)
 */
fun <T> List<T>.toggle(item: T): List<T> =
    if (contains(item)) filter { it != item } else this + item

/**
 * Boolean extensions
 */

/**
 * Executes block if true
 */
inline fun Boolean.ifTrue(block: () -> Unit): Boolean {
    if (this) block()
    return this
}

/**
 * Executes block if false
 */
inline fun Boolean.ifFalse(block: () -> Unit): Boolean {
    if (!this) block()
    return this
}
