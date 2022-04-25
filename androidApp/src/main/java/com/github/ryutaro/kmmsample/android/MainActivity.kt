package com.github.ryutaro.kmmsample.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.ryutaro.kmmsample.android.ui.screens.MainView
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Napier.base(DebugAntilog())
        setContent {
            MainView {
                TopAppBar(
                    title = {
                        when (it) {
                            0 -> Text(text = "World Clocks")
                            else -> Text(text = "Find Meeting")
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppTest() {
}
