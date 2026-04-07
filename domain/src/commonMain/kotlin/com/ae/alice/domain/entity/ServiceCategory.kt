package com.ae.alice.domain.entity

/**
 * Domain entity representing a service category.
 */
data class ServiceCategory(
    val id: String,
    val name: String,
    val tab: ServiceTab
)

/**
 * Which tab a service category belongs to.
 */
enum class ServiceTab {
    TAB_ONE,
    TAB_TWO
}
