package com.example.bni.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bni.R
import com.example.bni.ui.screen.component.CommonButton
import com.example.bni.utils.toFormat

@Composable
fun PaymentSuccessfulScreen(balance: Double = 0.0, onNextAction: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.Done,
            contentDescription = "Success",
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = stringResource(id = R.string.payment_successful),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(id = R.string.current_balance, balance.toFormat()),
            style = MaterialTheme.typography.titleMedium
        )
        CommonButton(title = stringResource(id = R.string.ok)) {
            onNextAction()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentSuccessfulScreenPreview() {
    PaymentSuccessfulScreen(balance = 500000.0, onNextAction = {})
}