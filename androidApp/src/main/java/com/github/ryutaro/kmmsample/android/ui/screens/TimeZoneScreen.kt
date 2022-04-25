package com.github.ryutaro.kmmsample.android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ryutaro.kmmsample.TimeZoneHelper
import com.github.ryutaro.kmmsample.TimeZoneHelperImpl
import com.github.ryutaro.kmmsample.android.ui.components.LocalTimeCard
import kotlinx.coroutines.delay

const val timeMillis = 1000 * 60L

@Composable
fun TimeZoneScreen(
    currentTimezoneStrings: SnapshotStateList<String>
) {
    val timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()
    val listState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        var time by remember { mutableStateOf(timezoneHelper.currentTime()) }

        LaunchedEffect(0) {
            while (true) {
                time = timezoneHelper.currentTime()
                delay(timeMillis)
            }
        }
        LocalTimeCard(
            city = timezoneHelper.currentTimeZone(),
            time = time,
            date = timezoneHelper.getDate(timezoneHelper.currentTimeZone())
        )
        Spacer(modifier = Modifier.size(16.dp))
    }
}
