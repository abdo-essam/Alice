package com.ae.alice.domain.entity

enum class Country(
    val countryName: String,
    val countryCodeName: String,
    val flagEmoji: String
) {
    ALGERIA("الجزائر", "DZ", "🇩🇿"),
    BAHRAIN("البحرين", "BH", "🇧🇭"),
    EGYPT("مصر", "EG", "🇪🇬"),
    IRAN("إيران", "IR", "🇮🇷"),
    IRAQ("العراق", "IQ", "🇮🇶"),
    JORDAN("الأردن", "JO", "🇯🇴"),
    KUWAIT("الكويت", "KW", "🇰🇼"),
    LEBANON("لبنان", "LB", "🇱🇧"),
    LIBYA("ليبيا", "LY", "🇱🇾"),
    MOROCCO("المغرب", "MA", "🇲🇦"),
    OMAN("عُمان", "OM", "🇴🇲"),
    PALESTINE("فلسطين", "PS", "🇵🇸"),
    QATAR("قطر", "QA", "🇶🇦"),
    SAUDI_ARABIA("السعودية", "SA", "🇸🇦"),
    SOMALIA("الصومال", "SO", "🇸🇴"),
    SUDAN("السودان", "SD", "🇸🇩"),
    SYRIA("سوريا", "SY", "🇸🇾"),
    TUNISIA("تونس", "TN", "🇹🇳"),
    UAE("الإمارات", "AE", "🇦🇪"),
    YEMEN("اليمن", "YE", "🇾🇪");

    companion object {
        fun default(): Country = UAE
    }
}
