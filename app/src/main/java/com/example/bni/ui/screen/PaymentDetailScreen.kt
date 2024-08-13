package com.example.bni.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bni.R
import com.example.bni.ui.screen.component.PaymentDetailComponent
import com.example.bni.utils.toFormat

@Composable
fun PaymentDetailScreen(
    qrString: String,
    onClickPay: (merchantName: String, transactionAmount: Double) -> Unit
) {

    val transactionId = qrString.mapPaymentDetail(index = 1)
    val merchantName = qrString.mapPaymentDetail(index = 2)
    val transactionAmount = qrString.mapPaymentDetail(index = 3)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.qris_payment_detail),
            style = MaterialTheme.typography.headlineMedium
        )
        PaymentDetailComponent(
            title = stringResource(id = R.string.merchant_name),
            value = merchantName
        )
        PaymentDetailComponent(
            title = stringResource(id = R.string.trx_amount),
            value = transactionAmount.toFormat()
        )
        PaymentDetailComponent(
            title = stringResource(id = R.string.trx_id),
            value = transactionId
        )
        Button(
            onClick = {
                onClickPay(merchantName, transactionAmount.toDouble())
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.pay_trx))
        }
    }
}

private fun String.mapPaymentDetail(index: Int): String {
    return if (this.count { it == '.' } == 3) {
        this.split(".")[index]
    } else {
        String()
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentDetailScreenPreview() {
    PaymentDetailScreen(
        qrString = "BNI.ID12345678.MERCHANT MOCK TEST.50000",
        onClickPay = { _, _ -> }
    )
}