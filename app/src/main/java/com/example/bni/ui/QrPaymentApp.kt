package com.example.bni.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bni.ui.navigation.Dashboard
import com.example.bni.ui.navigation.History
import com.example.bni.ui.navigation.PaymentDetail
import com.example.bni.ui.navigation.PaymentSuccessful
import com.example.bni.ui.navigation.ScanQr
import com.example.bni.ui.screen.DashboardScreen
import com.example.bni.ui.screen.PaymentDetailScreen
import com.example.bni.ui.screen.PaymentHistoryScreen
import com.example.bni.ui.screen.PaymentSuccessfulScreen
import com.example.bni.ui.screen.ScanQrScreen
import com.example.bni.ui.screen.event.PaymentEvent

@Composable
fun QrPaymentApp(
    viewModel: QrPaymentViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

    val balanceState by viewModel.balanceState.collectAsState()
    val paymentState by viewModel.paymentState.collectAsState()
    val historyState by viewModel.historyTransactionState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(PaymentEvent.InitializeData)
    }

    LaunchedEffect(key1 = paymentState) {
        if (paymentState) {
            navController.navigate(PaymentSuccessful)
            viewModel.onEvent(PaymentEvent.ResetPaymentState)
        }
    }

    NavHost(navController = navController, startDestination = Dashboard) {
        composable<Dashboard> {
            DashboardScreen(
                balance = balanceState.balance,
                onScanQrClicked = {
                    navController.navigate(ScanQr)
                }, onViewHistoryClicked = {
                    navController.navigate(History)
                }
            )
        }

        composable<ScanQr> {
            ScanQrScreen(qrString = {
                navController.navigate(PaymentDetail(qrString = it))
            })
        }

        composable<PaymentDetail> { backStackEntry ->
            val data = backStackEntry.toRoute<PaymentDetail>()
            PaymentDetailScreen(qrString = data.qrString) { merchantName, transactionAmount ->
                viewModel.onEvent(
                    PaymentEvent.OnClickPayment(
                        merchantName = merchantName,
                        transactionAmount = transactionAmount,
                    )
                )
            }
        }

        composable<PaymentSuccessful> {
            PaymentSuccessfulScreen(
                balance = balanceState.balance,
                onNextAction = {
                    navController.popBackStack(Dashboard, inclusive = false)
                })
        }

        composable<History> {
            PaymentHistoryScreen(state = historyState) {
                navController.navigateUp()
            }
        }
    }
}