package com.ae.alice.data.fake

import com.ae.alice.domain.entity.Place
import com.ae.alice.domain.entity.ServiceCategory
import com.ae.alice.domain.entity.ServiceTab
import com.ae.alice.domain.repository.PlaceRepository

class FakePlaceRepository : PlaceRepository {

    private val savedPlaceIds = mutableSetOf<String>()

    private val categories = listOf(
        // ── Tab 1: Sales ──
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

        // ── Tab 2: Washing & Detailing ──
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
        ServiceCategory("cat_29", "متجر أنظمة إنذار وتتبع سيارات (GPS)", ServiceTab.TAB_TWO),
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
        Place("p01", "معرض النخبة للسيارات", "شارع الملك فهد، الرياض", "cat_01", latitude = 24.73664, longitude = 46.64373, rating = 4.6, reviewCount = 255),
        Place("p02", "معرض الجزيرة للسيارات", "طريق الأمير سلطان، جدة", "cat_01", latitude = 21.49232, longitude = 39.20169, rating = 4.3, reviewCount = 180),
        Place("p03", "معرض الخليج للسيارات", "حي الروضة، الدمام", "cat_01", latitude = 26.37793, longitude = 50.04085, rating = 4.1, reviewCount = 120),
        Place("p04", "وكالة تويوتا - عبداللطيف جميل", "طريق خريص، الرياض", "cat_02", latitude = 24.66421, longitude = 46.72516, rating = 4.8, reviewCount = 420),
        Place("p05", "الجميح للسيارات", "طريق الملك عبدالعزيز، الرياض", "cat_02", latitude = 24.74322, longitude = 46.66478, rating = 4.5, reviewCount = 310),
        Place("p06", "حراج السيارات الرياض", "حي السلي، الرياض", "cat_03", latitude = 24.67419, longitude = 46.63261, rating = 3.9, reviewCount = 540),
        Place("p07", "معرض الثقة للسيارات المستعملة", "شارع التخصصي، جدة", "cat_03", latitude = 21.52108, longitude = 39.17919, rating = 4.0, reviewCount = 95),
        Place("p08", "شركة اليسر للتقسيط", "طريق العروبة، الرياض", "cat_04", latitude = 24.74764, longitude = 46.71028, rating = 4.2, reviewCount = 200),
        Place("p09", "تسهيل للتمويل", "حي الفيصلية، جدة", "cat_04", latitude = 21.50480, longitude = 39.17196, rating = 3.8, reviewCount = 150),
        Place("p10", "شركة المتحدة للاستيراد", "ميناء جدة، جدة", "cat_05", latitude = 21.48001, longitude = 39.21952, rating = 4.4, reviewCount = 88),
        Place("p11", "مؤسسة الصفوة للتصدير", "المنطقة الحرة، الدمام", "cat_05", latitude = 26.38142, longitude = 50.13316, rating = 4.0, reviewCount = 65),
        Place("p12", "مزاد الرياض للسيارات", "طريق الدائري الشرقي، الرياض", "cat_06", latitude = 24.70838, longitude = 46.65932, rating = 3.7, reviewCount = 320),
        Place("p13", "مزاد جدة للمركبات", "حي الجامعة، جدة", "cat_06", latitude = 21.46860, longitude = 39.18160, rating = 3.5, reviewCount = 210),
        Place("p14", "مركز الخبراء للصيانة", "شارع عثمان بن عفان، الرياض", "cat_07", latitude = 24.75101, longitude = 46.66353, rating = 4.7, reviewCount = 380),
        Place("p15", "مركز السرعة للصيانة", "طريق المدينة، جدة", "cat_07", latitude = 21.44836, longitude = 39.20865, rating = 4.3, reviewCount = 270),
        Place("p16", "مركز الإتقان لصيانة السيارات", "حي الخبر الشمالية، الخبر", "cat_07", latitude = 26.40995, longitude = 50.07365, rating = 4.5, reviewCount = 190),
        Place("p17", "ورشة الأمانة للميكانيكا", "حي السويدي، الرياض", "cat_08", latitude = 24.71593, longitude = 46.71062, rating = 4.1, reviewCount = 145),
        Place("p18", "ورشة الفهد للميكانيكا", "شارع فلسطين، جدة", "cat_08", latitude = 21.52646, longitude = 39.22242, rating = 4.0, reviewCount = 110),
        Place("p19", "ورشة النور لكهرباء السيارات", "حي العزيزية، الرياض", "cat_09", latitude = 24.73631, longitude = 46.64922, rating = 4.2, reviewCount = 95),
        Place("p20", "ورشة البرق للكهرباء", "حي المرجان، جدة", "cat_09", latitude = 21.50323, longitude = 39.22552, rating = 3.9, reviewCount = 78),
        Place("p21", "ورشة الإبداع للسمكرة والدهان", "حي النسيم، الرياض", "cat_10", latitude = 24.75965, longitude = 46.70398, rating = 4.4, reviewCount = 160),
        Place("p22", "ورشة اللمسة الذهبية", "طريق الحرمين، جدة", "cat_10", latitude = 21.43728, longitude = 39.20167, rating = 4.6, reviewCount = 220),
        Place("p23", "مركز فحص الدقة", "طريق أنس بن مالك، الرياض", "cat_11", latitude = 24.69812, longitude = 46.71792, rating = 4.5, reviewCount = 300),
        Place("p24", "مركز الفحص الشامل", "حي النعيم، جدة", "cat_11", latitude = 21.49555, longitude = 39.21992, rating = 4.3, reviewCount = 240),
        Place("p25", "مركز التقنية للبرمجة", "حي الملقا، الرياض", "cat_12", latitude = 24.74424, longitude = 46.68765, rating = 4.6, reviewCount = 170),
        Place("p26", "مركز الذكاء لتشخيص السيارات", "حي الحمراء، جدة", "cat_12", latitude = 21.47490, longitude = 39.14639, rating = 4.1, reviewCount = 130),
        Place("p27", "ورشة البرودة لتكييف السيارات", "حي المنصورة، الرياض", "cat_13", latitude = 24.72559, longitude = 46.65313, rating = 4.0, reviewCount = 85),
        Place("p28", "ورشة النسيم للتكييف", "حي بني مالك، جدة", "cat_13", latitude = 21.46541, longitude = 39.20895, rating = 3.8, reviewCount = 60),
        Place("p29", "ورشة القير الذهبي", "حي الشفا، الرياض", "cat_14", latitude = 24.72390, longitude = 46.62707, rating = 4.3, reviewCount = 140),
        Place("p30", "ورشة الحركة للفتيس", "حي الربوة، جدة", "cat_14", latitude = 21.45305, longitude = 39.15380, rating = 4.0, reviewCount = 100),
        Place("p31", "مركز الزيت السريع", "شارع التخصصي، الرياض", "cat_15", latitude = 24.71273, longitude = 46.66091, rating = 4.2, reviewCount = 250),
        Place("p32", "مركز موبيل للزيوت", "طريق الأمير ماجد، جدة", "cat_15", latitude = 21.44100, longitude = 39.22181, rating = 4.1, reviewCount = 180),
        Place("p33", "مركز بريدجستون الرياض", "طريق خريص، الرياض", "cat_16", latitude = 24.73428, longitude = 46.69955, rating = 4.5, reviewCount = 290),
        Place("p34", "مركز ميشلان جدة", "شارع حراء، جدة", "cat_16", latitude = 21.48891, longitude = 39.19904, rating = 4.4, reviewCount = 230),
        Place("p35", "مركز الميزان الدقيق", "حي الورود، الرياض", "cat_17", latitude = 24.70733, longitude = 46.64971, rating = 4.0, reviewCount = 75),
        Place("p36", "مركز الضبط المتقدم", "حي الصفا، جدة", "cat_17", latitude = 21.43889, longitude = 39.15617, rating = 3.9, reviewCount = 55),
        Place("p37", "مركز بطاريات أوبتيما", "حي الروابي، الرياض", "cat_18", latitude = 24.76090, longitude = 46.68350, rating = 4.3, reviewCount = 160),
        Place("p38", "مركز البطارية الذهبية", "حي السلامة، جدة", "cat_18", latitude = 21.43628, longitude = 39.18763, rating = 4.1, reviewCount = 120),
        Place("p39", "مغسلة اللمعة", "حي النرجس، الرياض", "cat_19", latitude = 24.69124, longitude = 46.66605, rating = 4.4, reviewCount = 340),
        Place("p40", "مغسلة البريق", "حي الشاطئ، جدة", "cat_19", latitude = 21.46041, longitude = 39.22873, rating = 4.2, reviewCount = 280),
        Place("p41", "مغسلة أوتو ووش", "طريق الملك سلمان، الرياض", "cat_20", latitude = 24.76305, longitude = 46.65003, rating = 4.6, reviewCount = 400),
        Place("p42", "مغسلة سبيد ووش", "طريق الكورنيش، جدة", "cat_20", latitude = 21.44521, longitude = 39.18895, rating = 4.3, reviewCount = 310),
        Place("p43", "مركز الألماس للتلميع", "حي الياسمين، الرياض", "cat_21", latitude = 24.70501, longitude = 46.70572, rating = 4.7, reviewCount = 200),
        Place("p44", "مركز الشاين للتلميع", "حي الروضة، جدة", "cat_21", latitude = 21.49856, longitude = 39.22021, rating = 4.5, reviewCount = 165),
        Place("p45", "مركز نانو شيلد", "حي حطين، الرياض", "cat_22", latitude = 24.68477, longitude = 46.63178, rating = 4.8, reviewCount = 180),
        Place("p46", "مركز سيراميك برو جدة", "حي الأندلس، جدة", "cat_22", latitude = 21.45102, longitude = 39.20872, rating = 4.6, reviewCount = 150),
        Place("p47", "مركز XPEL الرياض", "حي العليا، الرياض", "cat_23", latitude = 24.72671, longitude = 46.64111, rating = 4.9, reviewCount = 220),
        Place("p48", "مركز PPF جدة", "حي الزهراء، جدة", "cat_23", latitude = 21.52177, longitude = 39.15458, rating = 4.5, reviewCount = 140),
        Place("p49", "مركز الداخلية المتألقة", "حي الغدير، الرياض", "cat_24", latitude = 24.69016, longitude = 46.65212, rating = 4.3, reviewCount = 110),
        Place("p50", "مركز ديتيل كار", "حي المحمدية، جدة", "cat_24", latitude = 21.49332, longitude = 39.20271, rating = 4.4, reviewCount = 130),
        Place("p51", "متجر القطع الأصلية", "شارع الخزان، الرياض", "cat_25", latitude = 24.68585, longitude = 46.67910, rating = 4.2, reviewCount = 350),
        Place("p52", "مؤسسة الغيار السريع", "شارع الستين، جدة", "cat_25", latitude = 21.50016, longitude = 39.19949, rating = 4.0, reviewCount = 280),
        Place("p53", "متجر زينة السيارات", "حي السليمانية، الرياض", "cat_26", latitude = 24.75040, longitude = 46.67495, rating = 4.1, reviewCount = 190),
        Place("p54", "مركز الإكسسوار الفاخر", "حي الرويس، جدة", "cat_26", latitude = 21.47403, longitude = 39.23367, rating = 4.3, reviewCount = 160),
        Place("p55", "متجر ساوند كار", "حي المغرزات، الرياض", "cat_27", latitude = 24.73905, longitude = 46.71346, rating = 4.0, reviewCount = 85),
        Place("p56", "مركز الصوت العالي", "حي البوادي، جدة", "cat_27", latitude = 21.47750, longitude = 39.17460, rating = 3.9, reviewCount = 70),
        Place("p57", "مركز سيكوريت للزجاج", "حي الملز، الرياض", "cat_28", latitude = 24.66679, longitude = 46.62969, rating = 4.4, reviewCount = 200),
        Place("p58", "مركز الشفاف لزجاج السيارات", "حي النزهة، جدة", "cat_28", latitude = 21.52394, longitude = 39.15580, rating = 4.2, reviewCount = 150),
        Place("p59", "متجر الحماية الذكية", "حي الربيع، الرياض", "cat_29", latitude = 24.71089, longitude = 46.62971, rating = 4.3, reviewCount = 95),
        Place("p60", "مركز GPS تراكر", "حي الفيحاء، جدة", "cat_29", latitude = 21.46348, longitude = 39.22427, rating = 4.1, reviewCount = 75),
        Place("p61", "شركة بدجت للتأجير", "مطار الملك خالد، الرياض", "cat_30", latitude = 24.73412, longitude = 46.68364, rating = 4.5, reviewCount = 500),
        Place("p62", "شركة المفتاح لتأجير السيارات", "مطار الملك عبدالعزيز، جدة", "cat_30", latitude = 21.46000, longitude = 39.15787, rating = 4.2, reviewCount = 380),
        Place("p63", "سطحة الأمان", "حي السلام، الرياض", "cat_31", latitude = 24.66811, longitude = 46.63726, rating = 4.0, reviewCount = 220),
        Place("p64", "خدمة النقل السريع", "حي الفضل، جدة", "cat_31", latitude = 21.44326, longitude = 39.14436, rating = 3.8, reviewCount = 170),
        Place("p65", "خدمة الإنقاذ 24 ساعة", "تغطية شاملة، الرياض", "cat_32", latitude = 24.75883, longitude = 46.69819, rating = 4.6, reviewCount = 440),
        Place("p66", "المساعدة على الطريق VIP", "تغطية شاملة، جدة", "cat_32", latitude = 21.49906, longitude = 39.20008, rating = 4.4, reviewCount = 350),
        Place("p67", "شركة فحص بلس", "حي الصحافة، الرياض", "cat_33", latitude = 24.70852, longitude = 46.63791, rating = 4.7, reviewCount = 260),
        Place("p68", "مركز ما قبل الشراء", "حي الحمراء، جدة", "cat_33", latitude = 21.52365, longitude = 39.22218, rating = 4.3, reviewCount = 190),
        Place("p69", "مركز التعديل الاحترافي", "حي الشهداء، الرياض", "cat_34", latitude = 24.68217, longitude = 46.64412, rating = 4.5, reviewCount = 140),
        Place("p70", "ورشة الإبداع للتعديل", "حي المروة، جدة", "cat_34", latitude = 21.45647, longitude = 39.16387, rating = 4.2, reviewCount = 110),
        Place("p71", "مركز EV سيرفيس", "حي العارض، الرياض", "cat_35", latitude = 24.76355, longitude = 46.67746, rating = 4.8, reviewCount = 90),
        Place("p72", "مركز الكهربائية للصيانة", "حي الخالدية، جدة", "cat_35", latitude = 21.43716, longitude = 39.14527, rating = 4.4, reviewCount = 65),
        Place("p73", "محطة شحن إلكترون", "طريق الملك فيصل، الرياض", "cat_36", latitude = 24.71016, longitude = 46.65389, rating = 4.3, reviewCount = 180),
        Place("p74", "محطة الشحن السريع", "كورنيش جدة، جدة", "cat_36", latitude = 21.48719, longitude = 39.22115, rating = 4.5, reviewCount = 200),
        Place("p75", "مركز السمارت كار", "حي الإزدهار، الرياض", "cat_37", latitude = 24.66393, longitude = 46.65186, rating = 4.6, reviewCount = 110),
        Place("p76", "مركز التقنية الذكية", "حي الصفا، جدة", "cat_37", latitude = 21.49146, longitude = 39.22097, rating = 4.3, reviewCount = 85),
    )

    override suspend fun getCategories(): List<ServiceCategory> = categories

    override suspend fun getPlacesByCategory(categoryId: String, location: String): List<Place> =
        places.filter { it.categoryId == categoryId }.map {
            it.copy(isSaved = it.id in savedPlaceIds)
        }

    override suspend fun searchPlaces(query: String, location: String): List<Place> {
        if (query.isBlank()) return emptyList()
        return places.filter {
            it.name.contains(query, ignoreCase = true) ||
                it.address.contains(query, ignoreCase = true)
        }.map {
            it.copy(isSaved = it.id in savedPlaceIds)
        }
    }

    override suspend fun savePlace(placeId: String) {
        savedPlaceIds.add(placeId)
    }

    override suspend fun unsavePlace(placeId: String) {
        savedPlaceIds.remove(placeId)
    }
}
