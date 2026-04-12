package com.ae.alice.domain.entity

data class Country(
    val id: String,
    val countryName: String,
    val countryCodeName: String,
    val flagEmoji: String
) {
    companion object {
        fun default(): Country = Country("c_ae", "الإمارات", "AE", "🇦🇪")
    }
}
