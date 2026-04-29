package com.tecsup.examen02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tecsup.examen02.navigation.AppNavigation
import com.tecsup.examen02.ui.theme.Examen02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examen02Theme {
                AppNavigation()
            }
        }
    }
}