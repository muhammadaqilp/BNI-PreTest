package com.example.bni.ui.main.screen.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bni.R

@Composable
fun PortfolioLineChart(
    values: List<Float>,
    modifier: Modifier = Modifier,
    lineColor: Color = Color.DarkGray
) {
    val months = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )

    val nonNullValues = values.map { it }

    val transactions = months.zip(nonNullValues)

    val yAxisLabels = generateYAxisLabels(nonNullValues)
    val maxValue = nonNullValues.maxOrNull() ?: 0f
    val minValue = nonNullValues.minOrNull() ?: 0f

    Column(modifier = modifier) {
        Text(
            stringResource(id = R.string.portfolio_line_chart),
            fontSize = 18.sp,
            modifier = Modifier.padding(all = 8.dp)
        )

        Canvas(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            drawLineChart(transactions, yAxisLabels, lineColor, maxValue, minValue)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            months.forEach { label ->
                Text(
                    text = label,
                    style = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth()
                )
            }
        }
    }
}

fun generateYAxisLabels(values: List<Float>): List<String> {
    val minAmount = values.minOrNull() ?: 0f
    val maxAmount = values.maxOrNull() ?: 100f
    val step = (maxAmount - minAmount) / 5
    return List(6) { index -> "${(minAmount + step * index).toInt()}" }
}

fun DrawScope.drawLineChart(
    transactions: List<Pair<String, Float>>,
    yAxisLabels: List<String>,
    lineColor: Color,
    maxValue: Float,
    minValue: Float
) {
    val width = size.width
    val height = size.height
    val padding = 5.dp.toPx()

    if (transactions.isEmpty()) return

    val scaledPoints = transactions.mapIndexed { index, (_, amount) ->
        Offset(
            x = padding + (width - 2 * padding) * (index.toFloat() / (transactions.size - 1)),
            y = height - padding - (height - 2 * padding) * ((amount - minValue) / (maxValue - minValue))
        )
    }

    scaledPoints.zipWithNext().forEach { (start, end) ->
        drawLine(
            color = lineColor,
            start = start,
            end = end,
            strokeWidth = 3.dp.toPx()
        )
    }

    scaledPoints.forEach { point ->
        drawCircle(
            color = lineColor,
            radius = 5.dp.toPx(),
            center = point
        )
    }

    drawLine(
        color = Color.Gray,
        start = Offset(x = padding, y = height - padding),
        end = Offset(x = width - padding, y = height - padding),
        strokeWidth = 2.dp.toPx()
    )

    drawLine(
        color = Color.Gray,
        start = Offset(x = padding, y = padding),
        end = Offset(x = padding, y = height - padding),
        strokeWidth = 2.dp.toPx()
    )

    yAxisLabels.forEachIndexed { index, label ->
        drawContext.canvas.nativeCanvas.drawText(
            label,
            padding / 2,
            height - padding - (height - 2 * padding) * (index.toFloat() / (yAxisLabels.size - 1)) + 5.dp.toPx(),
            android.graphics.Paint().apply {
                textSize = 12.sp.toPx()
                color = android.graphics.Color.GRAY
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        PortfolioLineChart(
            values = listOf(3f, 7f, 8f, 10f, 5f, 10f, 1f, 3f, 5f, 10f, 7f, 7f),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
    }
}
