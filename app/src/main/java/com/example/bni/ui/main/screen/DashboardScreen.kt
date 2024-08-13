package com.example.bni.ui.main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bni.R
import com.example.bni.ui.main.screen.component.CommonButton
import com.example.bni.utils.toFormat

@Composable
fun DashboardScreen(
    balance: Double = 0.0,
    onScanQrClicked: () -> Unit,
    onViewHistoryClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_dashboard),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.current_balance, balance.toFormat())
        )
        CommonButton(title = stringResource(id = R.string.scan_qris)) {
            onScanQrClicked()
        }
        CommonButton(title = stringResource(id = R.string.view_trx_history)) {
            onViewHistoryClicked()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DashboardScreenPreview() {
    DashboardScreen(balance = 500000.0, onScanQrClicked = {}, onViewHistoryClicked = {})
}