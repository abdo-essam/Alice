package com.ae.alice.designsystem.components.datepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.AText
import com.ae.alice.designsystem.theme.ADimensions
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun AWheelDatePicker(
    years: List<Int>,
    months: List<String>,
    days: List<Int>,
    selectedYear: Int,
    selectedMonth: Int,
    selectedDay: Int,
    onYearChanged: (Int) -> Unit,
    onMonthChanged: (Int) -> Unit,
    onDayChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    itemHeight: Dp = 40.dp,
    choiceIndicatorColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    choiceIndicatorShape: Shape = RoundedCornerShape(ADimensions.RadiusMd),
    visibleItemCount: Int = 3,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Day picker
        VerticalPickerColumn(
            items = days.map { it.toString() },
            selectedIndex = days.indexOf(selectedDay).coerceAtLeast(0),
            onSelectedChanged = { index -> onDayChanged(days[index]) },
            itemHeight = itemHeight,
            visibleItemCount = visibleItemCount,
            choiceIndicatorColor = choiceIndicatorColor,
            choiceIndicatorShape = choiceIndicatorShape,
            modifier = Modifier.weight(1f),
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Month picker
        VerticalPickerColumn(
            items = months,
            selectedIndex = selectedMonth.coerceIn(0, months.lastIndex),
            onSelectedChanged = { index -> onMonthChanged(index) },
            itemHeight = itemHeight,
            visibleItemCount = visibleItemCount,
            choiceIndicatorColor = choiceIndicatorColor,
            choiceIndicatorShape = choiceIndicatorShape,
            modifier = Modifier.weight(1f),
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Year picker
        VerticalPickerColumn(
            items = years.map { it.toString() },
            selectedIndex = years.indexOf(selectedYear).coerceAtLeast(0),
            onSelectedChanged = { index -> onYearChanged(years[index]) },
            itemHeight = itemHeight,
            visibleItemCount = visibleItemCount,
            choiceIndicatorColor = choiceIndicatorColor,
            choiceIndicatorShape = choiceIndicatorShape,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun VerticalPickerColumn(
    items: List<String>,
    selectedIndex: Int,
    onSelectedChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    itemHeight: Dp = 40.dp,
    visibleItemCount: Int = 3,
    choiceIndicatorColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    choiceIndicatorShape: Shape = RoundedCornerShape(ADimensions.RadiusMd),
) {
    val pagerState = rememberPagerState(
        initialPage = selectedIndex,
        pageCount = { items.size }
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .distinctUntilChanged()
            .collect { page ->
                onSelectedChanged(page)
            }
    }

    Box(
        modifier = modifier.height(itemHeight * visibleItemCount),
        contentAlignment = Alignment.Center
    ) {
        // Choice indicator (middle highlighted area)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight)
                .clip(choiceIndicatorShape)
                .background(choiceIndicatorColor)
        )

        VerticalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(itemHeight),
            modifier = Modifier.height(itemHeight * visibleItemCount),
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight),
                contentAlignment = Alignment.Center
            ) {
                AText(
                    text = items[page],
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (page == pagerState.currentPage)
                        MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                )
            }
        }
    }
}
