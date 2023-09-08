package top.x86.demo.compose.more.modifiers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import top.x86.compose.more.modifiers.fadingEdgeHorizontal
import top.x86.compose.more.modifiers.fadingEdgeVertical
import top.x86.demo.compose.more.modifiers.ui.theme.BottomSheetPopupDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomSheetPopupDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VerticalScrollDemo()
                }
            }
        }
    }
}

@Composable
fun VerticalScrollDemo() {
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .fadingEdgeVertical(scrollState)
    ) {
        for (i in 0..100) {
            Text("item $i")
        }
    }
}

@Composable
fun HorizontalScrollDemo() {
    val scrollState = rememberScrollState()

    Row(
        Modifier
            .fillMaxSize()
            .horizontalScroll(scrollState)
            .fadingEdgeHorizontal(scrollState)
    ) {
        for (i in 0..100) {
            Text("item $i")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerticalScrollDemoPreview() {
    BottomSheetPopupDemoTheme {
        VerticalScrollDemo()
    }
}


@Preview(showBackground = true)
@Composable
fun HorizontalScrollDemoPreview() {
    BottomSheetPopupDemoTheme {
        HorizontalScrollDemo()
    }
}