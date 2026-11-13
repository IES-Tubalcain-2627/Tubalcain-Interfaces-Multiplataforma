package net.tubalcain.interfacesmultiplataforma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.tubalcain.interfacesmultiplataforma.planazoApp.PlanazoApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            PlanazoApp()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
//    RegisterScreen()
}

