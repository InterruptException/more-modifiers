package top.x86.compose.more.modifiers

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.dashBorder(radiusTop: Dp = 0.dp, radiusBottom: Dp = 0.dp,
                        dashColor: Color = Color.Gray,
                        strokeWidth: Dp = 1.dp,
                        radiusScale: Float = 2f,
                        intervals: FloatArray? = null
) = this.dashBorder(radiusTop, radiusTop, radiusBottom, radiusBottom, dashColor, strokeWidth, radiusScale, intervals)

fun Modifier.dashBorder(radius: Dp = 0.dp,
                        dashColor: Color = Color.Gray,
                        strokeWidth: Dp = 1.dp,
                        radiusScale: Float = 2f,
                        intervals: FloatArray? = null
) = this.dashBorder(radius, radius, radius, radius, dashColor, strokeWidth, radiusScale, intervals)

fun Modifier.dashBorder(
    radiusTopLeft: Dp = 0.dp,
    radiusTopRight: Dp = 0.dp,
    radiusBottomRight: Dp = 0.dp,
    radiusBottomLeft: Dp = 0.dp,
    dashColor: Color = Color.Gray,
    strokeWidth: Dp = 1.dp,
    radiusScale: Float = 2f,
    intervals: FloatArray? = null
) : Modifier {
    return this.drawBehind {

        val intervals2 = intervals?: floatArrayOf(10f, 10f)
        val dashPathEffect = PathEffect.dashPathEffect(intervals2, 0f)
        val strokeWidthPx = strokeWidth.toPx()
        val stroke = Stroke(width = strokeWidthPx, cap = StrokeCap.Butt, pathEffect = dashPathEffect)
        val topLeft = radiusTopLeft.toPx() * radiusScale
        val topRight = radiusTopRight.toPx() * radiusScale
        val bottomLeft = radiusBottomLeft.toPx() * radiusScale
        val bottomRight = radiusBottomRight.toPx() * radiusScale

        val path = Path().apply {
            moveTo(topLeft, 0f)
            lineTo(size.width - topRight, 0f)
            arcTo(Rect(size.width - topRight, 0f, size.width, topRight), 270f, 90f, false)
            lineTo(size.width, size.height - bottomRight)
            arcTo(Rect(size.width - bottomRight, size.height - bottomRight, size.width, size.height), 0f, 90f, false)
            lineTo(bottomLeft, size.height)
            arcTo(Rect(0f, size.height - bottomLeft, bottomLeft, size.height), 90f, 90f, false)
            lineTo(0f, topLeft)
            arcTo(Rect(0f, 0f, topLeft, topLeft), 180f, 90f, false)
            close()
        }

        drawPath(path = path, color = dashColor, style = stroke)
    }
}

// 绘制虚线边框
fun Modifier.dashBorder(lineWidth: Dp, lineColor: Color, dashLineOn: Float, dashLineOff: Float, cornerRadius: Dp, enableClip: Boolean = true): Modifier {
    val m = this.drawBehind {
        // 绘制虚线边框
        drawRoundRect(
            color = lineColor,
            style = Stroke(
                width = lineWidth.toPx(),
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(dashLineOn, dashLineOff),
                    phase = 0f
                )
            ),
            cornerRadius = CornerRadius(cornerRadius.toPx())
        )
    }
    return if (enableClip) {
        m.clip(RoundedCornerShape(cornerRadius))
    } else  {
        m
    }
}