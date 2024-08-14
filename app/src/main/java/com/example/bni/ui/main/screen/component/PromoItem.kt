package com.example.bni.ui.main.screen.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bni.R
import com.example.bni.model.PromoModel

@Composable
fun PromoItem(modifier: Modifier = Modifier, data: PromoModel) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(6.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = data.title,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = data.descPromo, modifier = Modifier.padding(4.dp))
            }
            Text(
                text = stringResource(id = R.string.voucher_available, data.promoCount),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .drawBehind {
                        drawCircle(color = Color.Gray, radius = this.size.maxDimension)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PromoItemPreview() {
    PromoItem(
        data = PromoModel(
            title = "Test Promo",
            desc = "Test Desc",
            descPromo = "Test Desc Promo",
            location = "Test Location",
            promoName = "Test Promo Name",
            promoCount = 2
        )
    )
}