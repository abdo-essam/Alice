package com.ae.alice.data.fake

import com.ae.alice.domain.entity.Place
import com.ae.alice.domain.entity.ServiceCategory
import com.ae.alice.domain.entity.ServiceTab
import com.ae.alice.domain.repository.PlaceRepository

/**
 * Fake implementation of PlaceRepository for development.
 * Contains categorized car-related service data.
 */
class FakePlaceRepository : PlaceRepository {

    private val categories = listOf(
        // ── Tab 1: Sales ──
        ServiceCategory("cat_01", "معرض سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_02", "معرض سيارات جديدة", ServiceTab.TAB_ONE),
        ServiceCategory("cat_03", "معرض سيارات مستعملة", ServiceTab.TAB_ONE),
        ServiceCategory("cat_04", "شركة بيع سيارات بالتقسيط", ServiceTab.TAB_ONE),
        ServiceCategory("cat_05", "شركة استيراد وتصدير سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_06", "مزاد سيارات", ServiceTab.TAB_ONE),
        // ── Tab 1: Maintenance ──
        ServiceCategory("cat_07", "مركز صيانة سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_08", "ورشة ميكانيكا سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_09", "ورشة كهرباء سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_10", "ورشة سمكرة ودهان", ServiceTab.TAB_ONE),
        ServiceCategory("cat_11", "مركز فحص سيارات شامل", ServiceTab.TAB_ONE),
        ServiceCategory("cat_12", "مركز برمجة وتشخيص سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_13", "ورشة تكييف سيارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_14", "ورشة ناقل الحركة (فتيس)", ServiceTab.TAB_ONE),
        // ── Tab 1: Oils / Tires / Batteries ──
        ServiceCategory("cat_15", "مركز تغيير زيوت", ServiceTab.TAB_ONE),
        ServiceCategory("cat_16", "مركز بيع وتركيب إطارات", ServiceTab.TAB_ONE),
        ServiceCategory("cat_17", "ميزان وضبط زوايا", ServiceTab.TAB_ONE),
        ServiceCategory("cat_18", "مركز بطاريات سيارات", ServiceTab.TAB_ONE),

        // ── Tab 2: Washing & Detailing ──
        ServiceCategory("cat_19", "مغسلة سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_20", "مغسلة سيارات أوتوماتيك", ServiceTab.TAB_TWO),
        ServiceCategory("cat_21", "مركز تلميع سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_22", "مركز نانو سيراميك", ServiceTab.TAB_TWO),
        ServiceCategory("cat_23", "مركز حماية الطلاء (PPF)", ServiceTab.TAB_TWO),
        ServiceCategory("cat_24", "مركز تنظيف داخلي سيارات", ServiceTab.TAB_TWO),
        // ── Tab 2: Parts & Accessories ──
        ServiceCategory("cat_25", "متجر قطع غيار سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_26", "مركز بيع إكسسوارات سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_27", "متجر أنظمة صوت سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_28", "مركز زجاج سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_29", "متجر إنذار وتتبع سيارات (GPS)", ServiceTab.TAB_TWO),
        // ── Tab 2: Services ──
        ServiceCategory("cat_30", "شركة تأجير سيارات", ServiceTab.TAB_TWO),
        ServiceCategory("cat_31", "شركة نقل سيارات (سطحة)", ServiceTab.TAB_TWO),
        ServiceCategory("cat_32", "خدمة المساعدة على الطريق", ServiceTab.TAB_TWO),
        ServiceCategory("cat_33", "شركة فحص سيارات قبل الشراء", ServiceTab.TAB_TWO),
        ServiceCategory("cat_34", "شركة تعديل وتخصيص سيارات", ServiceTab.TAB_TWO),
        // ── Tab 2: Electric ──
        ServiceCategory("cat_35", "مركز صيانة سيارات كهربائية", ServiceTab.TAB_TWO),
        ServiceCategory("cat_36", "محطة شحن سيارات كهربائية", ServiceTab.TAB_TWO),
        ServiceCategory("cat_37", "مركز برمجة سيارات ذكية", ServiceTab.TAB_TWO),
    )

    private val places = listOf(
        // ── cat_01: معرض سيارات ──
        Place("p01", "معرض النخبة للسيارات", "شارع الملك فهد، الرياض", "cat_01"),
        Place("p02", "معرض الجزيرة للسيارات", "طريق الأمير سلطان، جدة", "cat_01"),
        Place("p03", "معرض الخليج للسيارات", "حي الروضة، الدمام", "cat_01"),

        // ── cat_02: معرض سيارات جديدة ──
        Place("p04", "وكالة تويوتا - عبداللطيف جميل", "طريق خريص، الرياض", "cat_02"),
        Place("p05", "الجميح للسيارات", "طريق الملك عبدالعزيز، الرياض", "cat_02"),

        // ── cat_03: معرض سيارات مستعملة ──
        Place("p06", "حراج السيارات الرياض", "حي السلي، الرياض", "cat_03"),
        Place("p07", "معرض الثقة للسيارات المستعملة", "شارع التخصصي، جدة", "cat_03"),

        // ── cat_04: شركة بيع سيارات بالتقسيط ──
        Place("p08", "شركة اليسر للتقسيط", "طريق العروبة، الرياض", "cat_04"),
        Place("p09", "تسهيل للتمويل", "حي الفيصلية، جدة", "cat_04"),

        // ── cat_05: شركة استيراد وتصدير سيارات ──
        Place("p10", "شركة المتحدة للاستيراد", "ميناء جدة، جدة", "cat_05"),
        Place("p11", "مؤسسة الصفوة للتصدير", "المنطقة الحرة، الدمام", "cat_05"),

        // ── cat_06: مزاد سيارات ──
        Place("p12", "مزاد الرياض للسيارات", "طريق الدائري الشرقي، الرياض", "cat_06"),
        Place("p13", "مزاد جدة للمركبات", "حي الجامعة، جدة", "cat_06"),

        // ── cat_07: مركز صيانة سيارات ──
        Place("p14", "مركز الخبراء للصيانة", "شارع عثمان بن عفان، الرياض", "cat_07"),
        Place("p15", "مركز السرعة للصيانة", "طريق المدينة، جدة", "cat_07"),
        Place("p16", "مركز الإتقان لصيانة السيارات", "حي الخبر الشمالية، الخبر", "cat_07"),

        // ── cat_08: ورشة ميكانيكا سيارات ──
        Place("p17", "ورشة الأمانة للميكانيكا", "حي السويدي، الرياض", "cat_08"),
        Place("p18", "ورشة الفهد للميكانيكا", "شارع فلسطين، جدة", "cat_08"),

        // ── cat_09: ورشة كهرباء سيارات ──
        Place("p19", "ورشة النور لكهرباء السيارات", "حي العزيزية، الرياض", "cat_09"),
        Place("p20", "ورشة البرق للكهرباء", "حي المرجان، جدة", "cat_09"),

        // ── cat_10: ورشة سمكرة ودهان ──
        Place("p21", "ورشة الإبداع للسمكرة والدهان", "حي النسيم، الرياض", "cat_10"),
        Place("p22", "ورشة اللمسة الذهبية", "طريق الحرمين، جدة", "cat_10"),

        // ── cat_11: مركز فحص سيارات شامل ──
        Place("p23", "مركز فحص الدقة", "طريق أنس بن مالك، الرياض", "cat_11"),
        Place("p24", "مركز الفحص الشامل", "حي النعيم، جدة", "cat_11"),

        // ── cat_12: مركز برمجة وتشخيص سيارات ──
        Place("p25", "مركز التقنية للبرمجة", "حي الملقا، الرياض", "cat_12"),
        Place("p26", "مركز الذكاء لتشخيص السيارات", "حي الحمراء، جدة", "cat_12"),

        // ── cat_13: ورشة تكييف سيارات ──
        Place("p27", "ورشة البرودة لتكييف السيارات", "حي المنصورة، الرياض", "cat_13"),
        Place("p28", "ورشة النسيم للتكييف", "حي بني مالك، جدة", "cat_13"),

        // ── cat_14: ورشة ناقل الحركة (فتيس) ──
        Place("p29", "ورشة القير الذهبي", "حي الشفا، الرياض", "cat_14"),
        Place("p30", "ورشة الحركة للفتيس", "حي الربوة، جدة", "cat_14"),

        // ── cat_15: مركز تغيير زيوت ──
        Place("p31", "مركز الزيت السريع", "شارع التخصصي، الرياض", "cat_15"),
        Place("p32", "مركز موبيل للزيوت", "طريق الأمير ماجد، جدة", "cat_15"),

        // ── cat_16: مركز بيع وتركيب إطارات ──
        Place("p33", "مركز بريدجستون الرياض", "طريق خريص، الرياض", "cat_16"),
        Place("p34", "مركز ميشلان جدة", "شارع حراء، جدة", "cat_16"),

        // ── cat_17: ميزان وضبط زوايا ──
        Place("p35", "مركز الميزان الدقيق", "حي الورود، الرياض", "cat_17"),
        Place("p36", "مركز الضبط المتقدم", "حي الصفا، جدة", "cat_17"),

        // ── cat_18: مركز بطاريات سيارات ──
        Place("p37", "مركز بطاريات أوبتيما", "حي الروابي، الرياض", "cat_18"),
        Place("p38", "مركز البطارية الذهبية", "حي السلامة، جدة", "cat_18"),

        // ── cat_19: مغسلة سيارات ──
        Place("p39", "مغسلة اللمعة", "حي النرجس، الرياض", "cat_19"),
        Place("p40", "مغسلة البريق", "حي الشاطئ، جدة", "cat_19"),

        // ── cat_20: مغسلة سيارات أوتوماتيك ──
        Place("p41", "مغسلة أوتو ووش", "طريق الملك سلمان، الرياض", "cat_20"),
        Place("p42", "مغسلة سبيد ووش", "طريق الكورنيش، جدة", "cat_20"),

        // ── cat_21: مركز تلميع سيارات ──
        Place("p43", "مركز الألماس للتلميع", "حي الياسمين، الرياض", "cat_21"),
        Place("p44", "مركز الشاين للتلميع", "حي الروضة، جدة", "cat_21"),

        // ── cat_22: مركز نانو سيراميك ──
        Place("p45", "مركز نانو شيلد", "حي حطين، الرياض", "cat_22"),
        Place("p46", "مركز سيراميك برو جدة", "حي الأندلس، جدة", "cat_22"),

        // ── cat_23: مركز حماية الطلاء (PPF) ──
        Place("p47", "مركز XPEL الرياض", "حي العليا، الرياض", "cat_23"),
        Place("p48", "مركز PPF جدة", "حي الزهراء، جدة", "cat_23"),

        // ── cat_24: مركز تنظيف داخلي سيارات ──
        Place("p49", "مركز الداخلية المتألقة", "حي الغدير، الرياض", "cat_24"),
        Place("p50", "مركز ديتيل كار", "حي المحمدية، جدة", "cat_24"),

        // ── cat_25: متجر قطع غيار سيارات ──
        Place("p51", "متجر القطع الأصلية", "شارع الخزان، الرياض", "cat_25"),
        Place("p52", "مؤسسة الغيار السريع", "شارع الستين، جدة", "cat_25"),

        // ── cat_26: مركز بيع إكسسوارات سيارات ──
        Place("p53", "متجر زينة السيارات", "حي السليمانية، الرياض", "cat_26"),
        Place("p54", "مركز الإكسسوار الفاخر", "حي الرويس، جدة", "cat_26"),

        // ── cat_27: متجر أنظمة صوت سيارات ──
        Place("p55", "متجر ساوند كار", "حي المغرزات، الرياض", "cat_27"),
        Place("p56", "مركز الصوت العالي", "حي البوادي، جدة", "cat_27"),

        // ── cat_28: مركز زجاج سيارات ──
        Place("p57", "مركز سيكوريت للزجاج", "حي الملز، الرياض", "cat_28"),
        Place("p58", "مركز الشفاف لزجاج السيارات", "حي النزهة، جدة", "cat_28"),

        // ── cat_29: متجر إنذار وتتبع سيارات (GPS) ──
        Place("p59", "متجر الحماية الذكية", "حي الربيع، الرياض", "cat_29"),
        Place("p60", "مركز GPS تراكر", "حي الفيحاء، جدة", "cat_29"),

        // ── cat_30: شركة تأجير سيارات ──
        Place("p61", "شركة بدجت للتأجير", "مطار الملك خالد، الرياض", "cat_30"),
        Place("p62", "شركة المفتاح لتأجير السيارات", "مطار الملك عبدالعزيز، جدة", "cat_30"),

        // ── cat_31: شركة نقل سيارات (سطحة) ──
        Place("p63", "سطحة الأمان", "حي السلام، الرياض", "cat_31"),
        Place("p64", "خدمة النقل السريع", "حي الفضل، جدة", "cat_31"),

        // ── cat_32: خدمة المساعدة على الطريق ──
        Place("p65", "خدمة الإنقاذ 24 ساعة", "تغطية شاملة، الرياض", "cat_32"),
        Place("p66", "المساعدة على الطريق VIP", "تغطية شاملة، جدة", "cat_32"),

        // ── cat_33: شركة فحص سيارات قبل الشراء ──
        Place("p67", "شركة فحص بلس", "حي الصحافة، الرياض", "cat_33"),
        Place("p68", "مركز ما قبل الشراء", "حي الحمراء، جدة", "cat_33"),

        // ── cat_34: شركة تعديل وتخصيص سيارات ──
        Place("p69", "مركز التعديل الاحترافي", "حي الشهداء، الرياض", "cat_34"),
        Place("p70", "ورشة الإبداع للتعديل", "حي المروة، جدة", "cat_34"),

        // ── cat_35: مركز صيانة سيارات كهربائية ──
        Place("p71", "مركز EV سيرفيس", "حي العارض، الرياض", "cat_35"),
        Place("p72", "مركز الكهربائية للصيانة", "حي الخالدية، جدة", "cat_35"),

        // ── cat_36: محطة شحن سيارات كهربائية ──
        Place("p73", "محطة شحن إلكترون", "طريق الملك فيصل، الرياض", "cat_36"),
        Place("p74", "محطة الشحن السريع", "كورنيش جدة، جدة", "cat_36"),

        // ── cat_37: مركز برمجة سيارات ذكية ──
        Place("p75", "مركز السمارت كار", "حي الإزدهار، الرياض", "cat_37"),
        Place("p76", "مركز التقنية الذكية", "حي الصفا، جدة", "cat_37"),
    )

    override suspend fun getCategories(): List<ServiceCategory> = categories

    override suspend fun getPlacesByCategory(categoryId: String): List<Place> =
        places.filter { it.categoryId == categoryId }

    override suspend fun searchPlaces(query: String): List<Place> {
        if (query.isBlank()) return emptyList()
        return places.filter {
            it.name.contains(query, ignoreCase = true) ||
                it.address.contains(query, ignoreCase = true)
        }
    }
}
