package com.example.bni.ui.main.screen.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bni.R

@Composable
fun PortfolioDonutChart(
    data: List<Pair<String, Float>>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    val slices = data.map { (category, value) ->
        PieSlice(
            category = category,
            value = value,
            color = generateRandomColor()
        )
    }

    Column(modifier = modifier) {
        Text(
            stringResource(id = R.string.portfolio_donut_chart),
            fontSize = 18.sp,
            modifier = Modifier.padding(all = 8.dp)
        )

        Canvas(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            drawPieChart(slices)
        }

        Column(modifier = Modifier.padding(top = 16.dp)) {
            slices.forEach { slice ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onClick(slice.category) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(slice.color)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = slice.category,
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
            }
        }
    }
}

data class PieSlice(
    val category: String,
    val value: Float,
    val color: Color
)

private fun DrawScope.drawPieChart(slices: List<PieSlice>) {
    val total = slices.sumOf { it.value.toDouble() }
    val center = Offset(size.width / 2, size.height / 2)
    val radius = size.minDimension / 2
    var startAngle = -90f

    slices.forEach { slice ->
        val sweepAngle = (slice.value / total * 360f).toFloat()

        drawArc(
            color = slice.color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = true,
            topLeft = Offset(center.x - radius, center.y - radius),
            size = size.copy(width = 2 * radius, height = 2 * radius),
            style = androidx.compose.ui.graphics.drawscope.Fill
        )

        startAngle += sweepAngle
    }
}

private fun generateRandomColor(): Color {
    return Color(
        (0xFF000000.toInt() or (0xFFFFFF * Math.random()).toInt()).toLong()
    )
}

@Preview(showBackground = true)
@Composable
private fun PortfolioDonutChartPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        val data = listOf(
            "Category A" to 30f,
            "Category B" to 20f,
            "Category C" to 10f,
            "Category D" to 40f
        )

        PortfolioDonutChart(
            data = data,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            onClick = {}
        )
    }
}