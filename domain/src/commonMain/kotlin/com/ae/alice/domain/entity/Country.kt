package com.ae.alice.domain.entity

enum class Country(
    val countryName: String,
    val callingCode: String,
    val countryCodeName: String,
    val flagEmoji: String
) {
    ALGERIA("الجزائر", "+213", "DZ", "🇩🇿"),
    BAHRAIN("البحرين", "+973", "BH", "🇧🇭"),
    EGYPT("مصر", "+20", "EG", "🇪🇬"),
    IRAN("إيران", "+98", "IR", "🇮🇷"),
    IRAQ("العراق", "+964", "IQ", "🇮🇶"),
    JORDAN("الأردن", "+962", "JO", "🇯🇴"),
    KUWAIT("الكويت", "+965", "KW", "🇰🇼"),
    LEBANON("لبنان", "+961", "LB", "🇱🇧"),
    LIBYA("ليبيا", "+218", "LY", "🇱🇾"),
    MOROCCO("المغرب", "+212", "MA", "🇲🇦"),
    OMAN("عُمان", "+968", "OM", "🇴🇲"),
    PALESTINE("فلسطين", "+970", "PS", "🇵🇸"),
    QATAR("قطر", "+974", "QA", "🇶🇦"),
    SAUDI_ARABIA("السعودية", "+966", "SA", "🇸🇦"),
    SOMALIA("الصومال", "+252", "SO", "🇸🇴"),
    SUDAN("السودان", "+249", "SD", "🇸🇩"),
    SYRIA("سوريا", "+963", "SY", "🇸🇾"),
    TUNISIA("تونس", "+216", "TN", "🇹🇳"),
    UAE("الإمارات", "+971", "AE", "🇦🇪"),
    YEMEN("اليمن", "+967", "YE", "🇾🇪");

    companion object {
        fun default(): Country = UAE
    }
}
