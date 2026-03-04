package com.ae.alice.designsystem.components.datePicker

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

/**
 * MENA-style vertical wheel date picker.
 * Three side-by-side vertical pagers for month, day, and year.
 */
@Composable
fun WheelDatePicker(
    selectedDayIndex: Int,
    selectedMonthIndex: Int,
    selectedYearIndex: Int,
    days: List<String>,
    months: List<String>,
    years: List<String>,
    modifier: Modifier = Modifier,
    onDateChange: (dayIndex: Int, monthIndex: Int, yearIndex: Int) -> Unit,
) {
    val dayPagerState = rememberPagerState(
        initialPage = selectedDayIndex.coerceIn(0, days.size - 1),
        pageCount = { days.size }
    )
    val monthPagerState = rememberPagerState(
        initialPage = selectedMonthIndex.coerceIn(0, months.size - 1),
        pageCount = { months.size }
    )
    val yearPagerState = rememberPagerState(
        initialPage = selectedYearIndex.coerceIn(0, years.size - 1),
        pageCount = { years.size }
    )

    var rowWidth by remember { mutableStateOf(0) }

    LaunchedEffect(selectedDayIndex, selectedMonthIndex, selectedYearIndex) {
        val targetDay = selectedDayIndex.coerceIn(0, days.size - 1)
        val targetMonth = selectedMonthIndex.coerceIn(0, months.size - 1)
        val targetYear = selectedYearIndex.coerceIn(0, years.size - 1)

        coroutineScope {
            if (yearPagerState.currentPage != targetYear) {
                launch { yearPagerState.scrollToPage(targetYear) }
            }
            if (monthPagerState.currentPage != targetMonth) {
                launch { monthPagerState.scrollToPage(targetMonth) }
            }
            if (dayPagerState.currentPage != targetDay) {
                launch { dayPagerState.scrollToPage(targetDay) }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(244.dp),
        contentAlignment = Alignment.Center
    ) {
        ChoiceIndicator(
            modifier = Modifier
                .width(with(LocalDensity.current) { rowWidth.toDp() + 20.dp })
                .align(Alignment.Center)
        )

        Row(
            modifier = Modifier
                .onSizeChanged { rowWidth = it.width }
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            VerticalPicker(
                state = monthPagerState,
                options = months,
                modifier = Modifier.padding(end = Theme.spacing._32),
            )
            VerticalPicker(
                state = dayPagerState,
                options = days,
                modifier = Modifier.padding(end = 52.dp)
            )
            VerticalPicker(
                state = yearPagerState,
                options = years,
                isYearPicker = true
            )
        }
    }

    LaunchedEffect(dayPagerState, monthPagerState, yearPagerState) {
        snapshotFlow {
            Triple(
                dayPagerState.currentPage,
                monthPagerState.currentPage,
                yearPagerState.currentPage
            )
        }
            .distinctUntilChanged()
            .collect { (dayIndex, monthIndex, yearIndex) ->
                val userIsScrolling = dayPagerState.isScrollInProgress ||
                        monthPagerState.isScrollInProgress ||
                        yearPagerState.isScrollInProgress

                if (userIsScrolling) {
                    onDateChange(dayIndex, monthIndex, yearIndex)
                }
            }
    }
}

@Composable
private fun ChoiceIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(Theme.radius.md))
            .height(28.dp)
            .background(Theme.colorScheme.background.surfaceHigh)
    )
}

@Composable
private fun VerticalPicker(
    state: PagerState,
    options: List<String>,
    isYearPicker: Boolean = false,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()
    val baseStyle = Theme.typography.body.large

    val maxWidth = remember(options) {
        options.maxOf { option ->
            textMeasurer.measure(
                text = option,
                style = baseStyle
            ).size.width
        }
    }

    Box(
        modifier = modifier
            .width(with(LocalDensity.current) { maxWidth.toDp() })
            .fillMaxHeight()
    ) {
        VerticalPager(
            state = state,
            modifier = Modifier.fillMaxHeight().wrapContentWidth(),
            beyondViewportPageCount = 5,
            horizontalAlignment = Alignment.CenterHorizontally,
            pageSpacing = 4.dp,
            contentPadding = PaddingValues(vertical = 108.dp),
            pageSize = PageSize.Fixed(28.dp),
            flingBehavior = PagerDefaults.flingBehavior(
                state = state,
                pagerSnapDistance = PagerSnapDistance.atMost(state.pageCount),
                snapPositionalThreshold = 0f,
                snapAnimationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing)
            )
        ) { page ->
            val distance = (page - state.currentPage).absoluteValue

            val alpha = when (distance) {
                0 -> 1f
                1 -> 0.9f
                2 -> 0.5f
                else -> 0.2f
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .graphicsLayer {
                        this.alpha = alpha
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = options[page],
                    style = Theme.typography.label.medium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = if (isYearPicker) TextAlign.End else TextAlign.Start,
                    color = Theme.colorScheme.shadePrimary
                )
            }
        }
    }
}
