package com.github.ryutaro.kmmsample.android.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ryutaro.kmmsample.android.navigation.bottomNavigationItems
import com.github.ryutaro.kmmsample.android.theme.AppTheme
import com.github.ryutaro.kmmsample.android.ui.EmptyComposable
import com.github.ryutaro.kmmsample.android.ui.topBarFun
import com.github.ryutaro.kmmsample.android.utils.AddTimeZoneDialog

@Composable
fun MainView(actionBarFun: topBarFun = { EmptyComposable() }) {
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    val currentTimezoneStrings = remember { SnapshotStateList<String>() }

    AppTheme {
        Scaffold(
            topBar = {
                     actionBarFun(selectedIndex)
            },
            floatingActionButton = {
                                   if (selectedIndex == 0) {
                                       FloatingActionButton(
                                           modifier = Modifier.padding(16.dp),
                                           onClick = {
                                               showAddDialog = true
                                           }
                                       ) {
                                           Icon(
                                               imageVector = Icons.Default.Add,
                                               contentDescription = null
                                           )
                                       }
                                   }
            },
            bottomBar = {
                BottomNavigation {
                    bottomNavigationItems.forEachIndexed { index, bottomNavigationItem ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    imageVector = bottomNavigationItem.icon,
                                    contentDescription = bottomNavigationItem.iconContentDescription
                                )
                            },
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                            }
                        )
                    }
                }
            }
        ) {
            when (selectedIndex) {
                0 -> TimeZoneScreen(currentTimezoneStrings)
                else -> FindMeetingScreen(currentTimezoneStrings)
            }
            if (showAddDialog) {
                AddTimeZoneDialog(
                    onAdd = { newTimezones ->
                        showAddDialog = false
                        for (zone in newTimezones) {
                            if (!currentTimezoneStrings.contains(zone)) {
                                currentTimezoneStrings.add(zone)
                            }
                        }
                    },
                    onDismiss = {
                        showAddDialog = false
                    }
                )
            }
        }
    }
}
