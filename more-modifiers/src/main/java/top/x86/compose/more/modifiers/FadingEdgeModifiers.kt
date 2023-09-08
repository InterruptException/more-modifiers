package top.x86.compose.more.modifiers

import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.compose.foundation.ScrollState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.core.math.MathUtils

fun Modifier.fadingEdgeVertical(scrollState: ScrollState,
                                @FloatRange(from = 0.0, to = 1.0) edgeLengthPercent: Float = 0.1f,
                                @FloatRange(from = 0.0, to = 1.0) maxAlpha: Float = 0.5f,
                                @FloatRange(from = 1.0, to = 100.0) scale: Double = 20.0
) = fadingEdgeVertical(scrollState, 0f,
        edgeLengthPercent, maxAlpha, maxAlpha, scale)

fun Modifier.fadingEdgeVertical(scrollState: ScrollState,
                                @FloatRange(from = 0.0, to = 1.0) topEdgeLengthPercent: Float = 0.06f,
                                @FloatRange(from = 0.0, to = 1.0) bottomEdgeLengthPercent: Float = 0.06f,
                                @FloatRange(from = 0.0, to = 1.0) topEdgeMaxAlpha: Float = 0.5f,
                                @FloatRange(from = 0.0, to = 1.0) bottomEdgeMaxAlpha: Float = 0.5f,
                                @FloatRange(from = 1.0, to = 100.0) scale: Double = 20.0
) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        val topEdgeLength = scrollState.viewportSize * topEdgeLengthPercent
        val topEdgeTopOffset = scrollState.value.toFloat()
        val p1 = scrollState.value.toDouble() / scrollState.maxValue.toDouble() * scale
        val topEdgeAlphaRate = Math.atan(p1)/(Math.PI/2.0)
        val topEdgeAlpha = (topEdgeMaxAlpha * topEdgeAlphaRate).toFloat()
        //顶部边缘
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(Color.Transparent.copy(alpha = topEdgeAlpha), Color.Transparent),
                startY = topEdgeTopOffset,
                endY = topEdgeTopOffset + topEdgeLength
            ),
            topLeft = Offset(0f, topEdgeTopOffset),
            size = Size(this.size.width, topEdgeLength),
            blendMode = BlendMode.SrcOver,
        )

        val bottomEdgeLength = scrollState.viewportSize * bottomEdgeLengthPercent
        val bottomEdgeTopOffset = scrollState.viewportSize + scrollState.value.toFloat() - bottomEdgeLength
        val p2 = (1.0-scrollState.value.toDouble() / scrollState.maxValue.toDouble())*scale
        val bottomEdgeAlphaRate = Math.atan(p2)/(Math.PI/2.0)
        val bottomEdgeAlpha = (bottomEdgeMaxAlpha * bottomEdgeAlphaRate).toFloat()
        //底部边缘
        drawRect(
            brush = Brush.verticalGradient(
                listOf(Color.Transparent,Color.Transparent.copy(alpha = MathUtils.clamp(bottomEdgeAlpha, 0f, 1f))),
                startY = bottomEdgeTopOffset,
                endY =  bottomEdgeTopOffset+bottomEdgeLength
            ),
            topLeft = Offset(0f, bottomEdgeTopOffset),
            size = Size(this.size.width, bottomEdgeLength),
            blendMode = BlendMode.SrcOver,
        )
    }

fun Modifier.fadingEdgeHorizontal(scrollState: ScrollState,
                                  @FloatRange(from = 0.0, to = 1.0) edgeLengthPercent: Float = 0.1f,
                                  @FloatRange(from = 0.0, to = 1.0) maxAlpha: Float = 0.5f,
                                  @FloatRange(from = 1.0, to = 100.0) scale: Double = 20.0
) = fadingEdgeHorizontal(scrollState, 0f,
        edgeLengthPercent, maxAlpha, maxAlpha, scale)

fun Modifier.fadingEdgeHorizontal(scrollState: ScrollState,
                                  @FloatRange(from = 0.0, to = 1.0) leftEdgeLengthPercent: Float = 0.06f,
                                  @FloatRange(from = 0.0, to = 1.0) rightEdgeLengthPercent: Float = 0.06f,
                                  @FloatRange(from = 0.0, to = 1.0) leftEdgeMaxAlpha: Float = 0.5f,
                                  @FloatRange(from = 0.0, to = 1.0) rightEdgeMaxAlpha: Float = 0.5f,
                                  @FloatRange(from = 1.0, to = 100.0) scale: Double = 20.0
) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        val leftEdgeLength = scrollState.viewportSize * leftEdgeLengthPercent
        val leftEdgeOffsetX = scrollState.value.toFloat()
        val p1 = scrollState.value.toDouble() / scrollState.maxValue.toDouble() * scale
        val leftEdgeAlphaRate = Math.atan(p1)/(Math.PI/2.0)

        val leftEdgeAlpha = (leftEdgeMaxAlpha * leftEdgeAlphaRate).toFloat()
        //左侧边缘
        drawRect(
            brush = Brush.horizontalGradient(
                colors = listOf(Color.Transparent.copy(alpha = leftEdgeAlpha), Color.Transparent),
                startX = leftEdgeOffsetX,
                endX = leftEdgeOffsetX + leftEdgeLength
            ),
            topLeft = Offset(leftEdgeOffsetX, 0f),
            size = Size(leftEdgeLength, this.size.height),
            blendMode = BlendMode.SrcOver,
        )

        val rightEdgeLength = scrollState.viewportSize * rightEdgeLengthPercent
        val rightEdgeOffsetX = scrollState.viewportSize + scrollState.value.toFloat() - rightEdgeLength
        val p2 = (1.0 - scrollState.value.toDouble() / scrollState.maxValue.toDouble()) * scale
        val rightEdgeAlphaRate = Math.atan(p2)/(Math.PI/2.0)
        val rightEdgeAlpha = (rightEdgeMaxAlpha * rightEdgeAlphaRate).toFloat()
        //右侧边缘
        drawRect(
            brush = Brush.horizontalGradient(
                listOf(Color.Transparent,Color.Transparent.copy(alpha = MathUtils.clamp(rightEdgeAlpha, 0f, 1f))),
                startX = rightEdgeOffsetX,
                endX =  rightEdgeOffsetX+rightEdgeLength
            ),
            topLeft = Offset(rightEdgeOffsetX, 0f),
            size = Size(rightEdgeLength, this.size.height),
            blendMode = BlendMode.SrcOver,
        )
    }