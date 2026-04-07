package com.ae.alice.domain.entity

data class ServiceCategory(
    val id: String,
    val name: String,
    val tab: ServiceTab
)

enum class ServiceTab {
    TAB_ONE,
    TAB_TWO
}
