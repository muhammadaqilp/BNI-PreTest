package com.example.bni.ui.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bni.R
import com.example.bni.model.PromoModel
import com.example.bni.ui.main.screen.component.PromoDetailComponent
import com.example.bni.util.toHtml

@Composable
fun PromoDetailScreen(data: PromoModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        PromoDetailComponent(
            title = stringResource(id = R.string.voucher_name),
            value = data.title
        )
        PromoDetailComponent(
            title = stringResource(id = R.string.voucher_desc),
            value = data.desc.toHtml()
        )
        PromoDetailComponent(
            title = stringResource(id = R.string.voucher_location),
            value = stringResource(id = R.string.voucher_redeem_location, data.location)
        )
        PromoDetailComponent(
            title = stringResource(id = R.string.voucher_availability),
            value = pluralStringResource(
                id = R.plurals.voucher_count,
                data.promoCount,
                data.promoCount
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PromoDetailScreenPreview() {
    PromoDetailScreen(
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