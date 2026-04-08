package com.ae.alice.data.fake

import com.ae.alice.domain.entity.Country
import com.ae.alice.domain.entity.City
import com.ae.alice.domain.entity.Place
import com.ae.alice.domain.entity.ServiceCategory
import com.ae.alice.domain.entity.ServiceTab
import com.ae.alice.domain.repository.PlaceRepository

class FakePlaceRepository : PlaceRepository {

    private val savedPlaceIds = mutableSetOf<String>()

    private val cities = listOf(
        City("loc_dz_01", "الجزائر العاصمة", "DZ"),
        City("loc_dz_02", "وهران", "DZ"),
        City("loc_dz_03", "قسنطينة", "DZ"),
        City("loc_bh_01", "المنامة", "BH"),
        City("loc_bh_02", "المحرق", "BH"),
        City("loc_bh_03", "الرفاع", "BH"),
        City("loc_eg_01", "القاهرة", "EG"),
        City("loc_eg_02", "الإسكندرية", "EG"),
        City("loc_eg_03", "الجيزة", "EG"),
        City("loc_ir_01", "طهران", "IR"),
        City("loc_ir_02", "مشهد", "IR"),
        City("loc_ir_03", "أصفهان", "IR"),
        City("loc_iq_01", "بغداد", "IQ"),
        City("loc_iq_02", "البصرة", "IQ"),
        City("loc_iq_03", "أربيل", "IQ"),
        City("loc_jo_01", "عمان", "JO"),
        City("loc_jo_02", "إربد", "JO"),
        City("loc_jo_03", "الزرقاء", "JO"),
        City("loc_kw_01", "مدينة الكويت", "KW"),
        City("loc_kw_02", "الأحمدي", "KW"),
        City("loc_kw_03", "الفروانية", "KW"),
        City("loc_lb_01", "بيروت", "LB"),
        City("loc_lb_02", "طرابلس", "LB"),
        City("loc_lb_03", "صيدا", "LB"),
        City("loc_ly_01", "طرابلس", "LY"),
        City("loc_ly_02", "بنغازي", "LY"),
        City("loc_ly_03", "مصراتة", "LY"),
        City("loc_ma_01", "الدار البيضاء", "MA"),
        City("loc_ma_02", "الرباط", "MA"),
        City("loc_ma_03", "فاس", "MA"),
        City("loc_om_01", "مسقط", "OM"),
        City("loc_om_02", "صلالة", "OM"),
        City("loc_om_03", "صحار", "OM"),
        City("loc_ps_01", "القدس", "PS"),
        City("loc_ps_02", "غزة", "PS"),
        City("loc_ps_03", "رام الله", "PS"),
        City("loc_qa_01", "الدوحة", "QA"),
        City("loc_qa_02", "الريان", "QA"),
        City("loc_qa_03", "الوكرة", "QA"),
        City("loc_sa_01", "الرياض", "SA"),
        City("loc_sa_02", "جدة", "SA"),
        City("loc_sa_03", "مكة المكرمة", "SA"),
        City("loc_so_01", "مقديشو", "SO"),
        City("loc_so_02", "هرجيسا", "SO"),
        City("loc_so_03", "كسمايو", "SO"),
        City("loc_sd_01", "الخرطوم", "SD"),
        City("loc_sd_02", "أم درمان", "SD"),
        City("loc_sd_03", "بورتسودان", "SD"),
        City("loc_sy_01", "دمشق", "SY"),
        City("loc_sy_02", "حلب", "SY"),
        City("loc_sy_03", "حمص", "SY"),
        City("loc_tn_01", "تونس العاصمة", "TN"),
        City("loc_tn_02", "صفاقس", "TN"),
        City("loc_tn_03", "سوسة", "TN"),
        City("loc_01", "أبوظبي", "AE"),
        City("loc_02", "دبي", "AE"),
        City("loc_03", "الشارقة", "AE"),
        City("loc_04", "عجمان", "AE"),
        City("loc_05", "أم القيوين", "AE"),
        City("loc_06", "رأس الخيمة", "AE"),
        City("loc_07", "الفجيرة", "AE"),
        City("loc_08", "العين", "AE"),
        City("loc_09", "خورفكان", "AE"),
        City("loc_10", "دبا الفجيرة", "AE"),
        City("loc_11", "مدينة زايد", "AE"),
        City("loc_ye_01", "صنعاء", "YE"),
        City("loc_ye_02", "عدن", "YE"),
        City("loc_ye_03", "تعز", "YE"),
    )

    private val categories = listOf(
        ServiceCategory("cat_01", "معرض سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_02", "معرض سيارات جديدة", ServiceTab.TAB_ONE),
        ServiceCategory("cat_03", "معرض سيارات مستعملة", ServiceTab.TAB_ONE),
        ServiceCategory("cat_04", "شركة بيع سيارات بالتقسيط", ServiceTab.TAB_ONE),
        ServiceCategory("cat_05", "شركة استيراد وتصدير سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_06", "مزاد سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_07", "مركز صيانة سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_08", "ورشة ميكانيكا سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_09", "ورشة كهرباء سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_10", "ورشة سمكرة ودهان", ServiceTab.TAB_ONE),
        ServiceCategory("cat_11", "مركز فحص سيارات شامل", ServiceTab.TAB_ONE),
        ServiceCategory("cat_12", "مركز برمجة وتشخيص سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_13", "ورشة تكييف سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_14", "ورشة ناقل الحركة (فتيس)", ServiceTab.TAB_ONE),
        ServiceCategory("cat_15", "مركز تغيير زيوت", ServiceTab.TAB_ONE),
        ServiceCategory("cat_16", "مركز بيع وتركيب إطارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_17", "ميزان وضبط زوايا", ServiceTab.TAB_ONE),
        ServiceCategory("cat_18", "مركز بطاريات سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_19", "مغسلة سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_20", "مغسلة سيارات أوتوماتيك", ServiceTab.TAB_TWO),
        ServiceCategory("cat_21", "مركز تلميع سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_22", "مركز نانو سيراميك", ServiceTab.TAB_TWO),
        ServiceCategory("cat_23", "مركز حماية الطلاء (PPF)", ServiceTab.TAB_TWO),
        ServiceCategory("cat_24", "مركز تنظيف داخلي سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_25", "متجر قطع غيار سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_26", "مركز بيع إكسسوارات سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_27", "متجر أنظمة صوت سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_28", "مركز زجاج سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_29", "متجر إنذار وتتبع سيارات (GPS)", ServiceTab.TAB_TWO),
        ServiceCategory("cat_30", "شركة تأجير سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_31", "شركة نقل سيارات (سطحة)", ServiceTab.TAB_TWO),
        ServiceCategory("cat_32", "خدمة المساعدة على الطريق", ServiceTab.TAB_TWO),
        ServiceCategory("cat_33", "شركة فحص سيارات قبل الشراء", ServiceTab.TAB_TWO),
        ServiceCategory("cat_34", "شركة تعديل وتخصيص سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_35", "مركز صيانة سيارات كهربائية", ServiceTab.TAB_TWO),
        ServiceCategory("cat_36", "محطة شحن سيارات كهربائية", ServiceTab.TAB_TWO),
        ServiceCategory("cat_37", "مركز برمجة سيارات ذكية", ServiceTab.TAB_TWO),
    )

    private val places = listOf(
        Place("p01", "معرض النخبة للسيارات", "شارع الشيخ زايد، دبي", "cat_01", imageUrl = "https://images.unsplash.com/photo-1567449303078-57ad995bd329?w=400", latitude = 25.2048, longitude = 55.2708),
        Place("p02", "معرض الجزيرة للسيارات", "شارع الكورنيش، أبوظبي", "cat_01", imageUrl = "https://images.unsplash.com/photo-1580273916550-e323be2ae537?w=400", latitude = 24.4539, longitude = 54.3773),
        Place("p03", "معرض الخليج للسيارات", "المنطقة الصناعية، الشارقة", "cat_01", imageUrl = "https://images.unsplash.com/photo-1549317661-bd32c8ce0abb?w=400", latitude = 25.3463, longitude = 55.4209),
        Place("p04", "وكالة تويوتا الفطيم", "شارع الشيخ زايد، دبي", "cat_02", imageUrl = "https://images.unsplash.com/photo-1552519507-da3b142c6e3d?w=400", latitude = 25.1985, longitude = 55.2796),
        Place("p05", "الغانم للسيارات", "شارع المرور، أبوظبي", "cat_02", imageUrl = "https://images.unsplash.com/photo-1542362567-b07e54358753?w=400", latitude = 24.4669, longitude = 54.3666),
        Place("p06", "سوق السيارات المستعملة", "رأس الخور، دبي", "cat_03", imageUrl = "https://images.unsplash.com/photo-1583121274602-3e2820c69888?w=400", latitude = 25.1845, longitude = 55.3390),
        Place("p07", "معرض الثقة للسيارات المستعملة", "المنطقة الحرة، عجمان", "cat_03", imageUrl = "https://images.unsplash.com/photo-1503376780353-7e6692767b70?w=400", latitude = 25.4052, longitude = 55.5136),
        Place("p08", "شركة اليسر للتقسيط", "شارع الاتحاد، دبي", "cat_04", imageUrl = "https://images.unsplash.com/photo-1560958089-b8a1929cea89?w=400", latitude = 25.2532, longitude = 55.3320),
        Place("p09", "تسهيل للتمويل", "شارع حمدان، أبوظبي", "cat_04", imageUrl = "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?w=400", latitude = 24.4870, longitude = 54.3567),
        Place("p10", "شركة المتحدة للاستيراد", "ميناء جبل علي، دبي", "cat_05", imageUrl = "https://images.unsplash.com/photo-1619767886558-efdc259cde1a?w=400", latitude = 25.0045, longitude = 55.0637),
        Place("p11", "مؤسسة الصفوة للتصدير", "المنطقة الحرة، رأس الخيمة", "cat_05", imageUrl = "https://images.unsplash.com/photo-1605559424843-9e4c228bf1c2?w=400", latitude = 25.7895, longitude = 55.9432),
        Place("p12", "مزاد الإمارات للسيارات", "القوز، دبي", "cat_06", imageUrl = "https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=400", latitude = 25.1425, longitude = 55.2367),
        Place("p13", "مزاد أبوظبي للمركبات", "المصفح، أبوظبي", "cat_06", imageUrl = "https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=400", latitude = 24.3707, longitude = 54.5184),
        Place("p14", "مركز الخبراء للصيانة", "القصيص، دبي", "cat_07", imageUrl = "https://images.unsplash.com/photo-1486262715619-67b85e0b08d3?w=400", latitude = 25.2783, longitude = 55.3722),
        Place("p15", "مركز السرعة للصيانة", "المصفح الصناعية، أبوظبي", "cat_07", imageUrl = "https://images.unsplash.com/photo-1530046339160-ce3e530c7d2f?w=400", latitude = 24.3579, longitude = 54.4991),
        Place("p16", "مركز الإتقان لصيانة السيارات", "المنطقة الصناعية، الشارقة", "cat_07", imageUrl = "https://images.unsplash.com/photo-1625047509248-ec889cbff17f?w=400", latitude = 25.3148, longitude = 55.3914),
        Place("p17", "ورشة الأمانة للميكانيكا", "الورقاء، دبي", "cat_08", imageUrl = "https://images.unsplash.com/photo-1619642751034-765dfdf7c58e?w=400", latitude = 25.1838, longitude = 55.4209),
        Place("p18", "ورشة الفهد للميكانيكا", "النهدة، الشارقة", "cat_08", imageUrl = "https://images.unsplash.com/photo-1622186477895-f2af6a0f5a97?w=400", latitude = 25.3095, longitude = 55.3708),
        Place("p19", "ورشة النور لكهرباء السيارات", "الراشدية، دبي", "cat_09", imageUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400", latitude = 25.2288, longitude = 55.3897),
        Place("p20", "ورشة البرق للكهرباء", "الجرف الصناعية، عجمان", "cat_09", imageUrl = "https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?w=400", latitude = 25.4328, longitude = 55.5003),
        Place("p39", "مغسلة اللمعة", "البرشاء، دبي", "cat_19", imageUrl = "https://images.unsplash.com/photo-1520340356584-f9917d1eea6f?w=400", latitude = 25.1134, longitude = 55.2001),
        Place("p40", "مغسلة البريق", "الخالدية، أبوظبي", "cat_19", imageUrl = "https://images.unsplash.com/photo-1601055903647-ddf1ee9a94a1?w=400", latitude = 24.4625, longitude = 54.3526),
        Place("p41", "مغسلة أوتو ووش", "جميرا، دبي", "cat_20", imageUrl = "https://images.unsplash.com/photo-1558618666-fcd25c85f82e?w=400", latitude = 25.2095, longitude = 55.2540),
        Place("p42", "مغسلة سبيد ووش", "الممزر، الشارقة", "cat_20", imageUrl = "https://images.unsplash.com/photo-1607860108855-64acf2078ed9?w=400", latitude = 25.3293, longitude = 55.3521),
        Place("p43", "مركز الألماس للتلميع", "المنطقة الصناعية، دبي", "cat_21", imageUrl = "https://images.unsplash.com/photo-1600712242805-5f78671b24da?w=400", latitude = 25.1752, longitude = 55.2658),
        Place("p44", "مركز الشاين للتلميع", "التعاون، الشارقة", "cat_21", imageUrl = "https://images.unsplash.com/photo-1618843479313-40f8afb4b4d8?w=400", latitude = 25.3244, longitude = 55.3874),
        Place("p45", "مركز نانو شيلد", "البدع، دبي", "cat_22", imageUrl = "https://images.unsplash.com/photo-1617531653332-bd46c24f2068?w=400", latitude = 25.2286, longitude = 55.2554),
        Place("p46", "مركز سيراميك برو", "مدينة محمد بن زايد، أبوظبي", "cat_22", imageUrl = "https://images.unsplash.com/photo-1603584173870-7f23fdae1b7a?w=400", latitude = 24.3419, longitude = 54.5419),
    )

    private val countries = listOf(
        Country("c_dz", "الجزائر", "DZ", "🇩🇿"),
        Country("c_bh", "البحرين", "BH", "🇧🇭"),
        Country("c_eg", "مصر", "EG", "🇪🇬"),
        Country("c_ir", "إيران", "IR", "🇮🇷"),
        Country("c_iq", "العراق", "IQ", "🇮🇶"),
        Country("c_jo", "الأردن", "JO", "🇯🇴"),
        Country("c_kw", "الكويت", "KW", "🇰🇼"),
        Country("c_lb", "لبنان", "LB", "🇱🇧"),
        Country("c_ly", "ليبيا", "LY", "🇱🇾"),
        Country("c_ma", "المغرب", "MA", "🇲🇦"),
        Country("c_om", "عُمان", "OM", "🇴🇲"),
        Country("c_ps", "فلسطين", "PS", "🇵🇸"),
        Country("c_qa", "قطر", "QA", "🇶🇦"),
        Country("c_sa", "السعودية", "SA", "🇸🇦"),
        Country("c_so", "الصومال", "SO", "🇸🇴"),
        Country("c_sd", "السودان", "SD", "🇸🇩"),
        Country("c_sy", "سوريا", "SY", "🇸🇾"),
        Country("c_tn", "تونس", "TN", "🇹🇳"),
        Country("c_ae", "الإمارات", "AE", "🇦🇪"),
        Country("c_ye", "اليمن", "YE", "🇾🇪")
    )

    override suspend fun getCountries(): List<Country> = countries

    override suspend fun getCategories(): List<ServiceCategory> = categories

    override suspend fun getCities(countryCode: String?): List<City> = 
        if (countryCode == null) cities else cities.filter { it.countryCode == countryCode }

    override suspend fun getPlacesByCategory(categoryId: String, city: String): List<Place> =
        places.filter { it.categoryId == categoryId }.map {
            it.copy(isSaved = it.id in savedPlaceIds)
        }

    override suspend fun searchPlaces(query: String, city: String): List<Place> {
        if (query.isBlank()) return emptyList()
        return places.filter {
            it.name.contains(query, ignoreCase = true) ||
                it.address.contains(query, ignoreCase = true)
        }.map { it.copy(isSaved = it.id in savedPlaceIds) }
    }

    override suspend fun savePlace(placeId: String) { savedPlaceIds.add(placeId) }
    override suspend fun unsavePlace(placeId: String) { savedPlaceIds.remove(placeId) }
}
