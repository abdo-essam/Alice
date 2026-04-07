package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.CarModel

@Composable
fun CarDetailsContent(
    model: CarModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeroImageSection(
            imageUrl = model.imageUrl,
            contentDescription = model.name
        )

        val isArabic = com.ae.alice.designsystem.locale.LocalAppLocale.current.language == com.ae.alice.designsystem.locale.AppLanguage.ARABIC

        model.description?.let { originalDescription ->
            val description = if (isArabic) {
                when (model.brandId) {
                    "1", "3", "7", "8", "9" -> "نظرة عامة\n\nتتميز هذه السيارة بتصميم رياضي فاخر، وتكنولوجيا متقدمة، وأداء عالٍ، مما يضمن لك تجربة قيادة استثنائية."
                    "2", "4", "5", "6", "10" -> "نظرة عامة\n\nتجمع هذه السيارة بين الاقتصاد في استهلاك الوقود والمميزات العصرية والراحة في القيادة، مما يجعلها خياراً مثالياً للاستخدام اليومي."
                    else -> "نظرة عامة\n\nسيارة عصرية تقدم لك كل ما تحتاجه من راحة، أمان، وأداء قوي على الطريق بأفضل المعايير العالمية."
                }
            } else {
                originalDescription
            }

            Spacer(modifier = Modifier.height(Theme.spacing._20))

            SectionDivider()

            Spacer(modifier = Modifier.height(Theme.spacing._20))

            DescriptionSection(description = description)
        }

        Spacer(modifier = Modifier.height(Theme.spacing._40))
    }
}
