package com.example.bni.payment

import com.example.bni.ui.QrPaymentViewModel
import com.example.bni.ui.screen.event.PaymentEvent
import com.example.bni.ui.state.HistoryTransactionState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class QrPaymentViewModelTest {

    private lateinit var viewModel: QrPaymentViewModel

    @Before
    fun setup() {
        viewModel = QrPaymentViewModel()
    }

    @Test
    fun onEvent_InitializeData_Success() {
        viewModel.onEvent(PaymentEvent.InitializeData)

        assertEquals(INITIAL_BALANCE, viewModel.balanceState.value.balance, 0.0)
    }

    @Test
    fun onEvent_OnClickPayment_Success() {
        viewModel.onEvent(PaymentEvent.InitializeData)
        viewModel.onEvent(
            PaymentEvent.OnClickPayment(
                merchantName = "Toko Berkah",
                transactionAmount = 50000.0
            )
        )

        val historyList = listOf(
            HistoryTransactionState(
                merchantName = "Toko Berkah",
                transactionAmount = 50000.0
            )
        )

        assertTrue(viewModel.paymentState.value)
        assertEquals(CURRENT_BALANCE, viewModel.balanceState.value.balance, 0.0)
        assertEquals(
            historyList[0].merchantName,
            viewModel.historyTransactionState.value[0].merchantName
        )
        assertEquals(
            historyList[0].transactionAmount,
            viewModel.historyTransactionState.value[0].transactionAmount,
            0.0
        )
        assertEquals(historyList.size, viewModel.historyTransactionState.value.size)
    }

    @Test
    fun onEvent_ResetPaymentState_Success() {
        viewModel.onEvent(PaymentEvent.ResetPaymentState)

        assertFalse(viewModel.paymentState.value)
    }

    companion object {
        private const val INITIAL_BALANCE = 550000.0
        private const val CURRENT_BALANCE = 500000.0
    }

}