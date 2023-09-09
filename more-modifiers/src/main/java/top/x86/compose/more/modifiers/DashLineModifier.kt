package top.x86.compose.more.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp

/**
 * # 绘制虚线
 *
 * [percentStartX] 起点X坐标的比例
 *
 * [percentStartY] 起点Y坐标的比例
 *
 * [percentEndX] 终点X坐标的比例
 *
 * [percentEndY] 终点Y坐标的比例
 */
fun Modifier.dashLine(lineWidth: Dp, dashColor: Color,
                      percentStartX: Float = 0f,
                      percentStartY: Float = 0.5f,
                      percentEndX: Float = 1f,
                      percentEndY: Float = 0.5f,
                      cap: StrokeCap = StrokeCap.Round,
                      intervals: FloatArray? = null
) : Modifier {
    return this.drawWithContent {
        val intervals2 = intervals?: floatArrayOf(10f, 10f)
        val dashPathEffect = PathEffect.dashPathEffect(intervals2, 0f)
        val strokeWidthPx = lineWidth.toPx()

        drawLine(color = dashColor,
            start = Offset(
                x = size.width * percentStartX,
                y = size.height * percentStartY
            ),
            end = Offset(
                x = size.width * percentEndX,
                y = size.height * percentEndY
            ),
            cap = cap,
            strokeWidth = strokeWidthPx, pathEffect = dashPathEffect,
        )
    }
}