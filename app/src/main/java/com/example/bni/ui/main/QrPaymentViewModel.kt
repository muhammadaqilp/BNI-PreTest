package com.example.bni.ui.main

import androidx.lifecycle.ViewModel
import com.example.bni.ui.main.screen.event.PaymentEvent
import com.example.bni.ui.main.state.BalanceState
import com.example.bni.ui.main.state.HistoryTransactionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QrPaymentViewModel @Inject constructor() : ViewModel() {

    private val _balanceState = MutableStateFlow(BalanceState())
    val balanceState: StateFlow<BalanceState> = _balanceState.asStateFlow()

    private val _historyTransactionState = MutableStateFlow<List<HistoryTransactionState>>(listOf())
    val historyTransactionState: StateFlow<List<HistoryTransactionState>> =
        _historyTransactionState.asStateFlow()

    private val _paymentState = MutableStateFlow(false)
    val paymentState: StateFlow<Boolean> = _paymentState.asStateFlow()

    fun onEvent(event: PaymentEvent) {
        when (event) {
            is PaymentEvent.InitializeData -> {
                updateBalance(balance = INITIAL_BALANCE)
            }

            is PaymentEvent.OnClickPayment -> {
                updatePaymentState(state = true)
                addToHistoryList(
                    merchantName = event.merchantName,
                    transactionAmount = event.transactionAmount
                )
                updateBalance(balance = _balanceState.value.balance - event.transactionAmount)
            }

            PaymentEvent.ResetPaymentState -> {
                updatePaymentState(state = false)
            }
        }
    }

    private fun updatePaymentState(state: Boolean) {
        _paymentState.update { state }
    }

    private fun updateBalance(balance: Double) {
        _balanceState.update { BalanceState(balance = balance) }
    }

    private fun addToHistoryList(merchantName: String, transactionAmount: Double) {
        val list = _historyTransactionState.value.toMutableList()
        list.add(
            HistoryTransactionState(
                merchantName = merchantName,
                transactionAmount = transactionAmount
            )
        )
        _historyTransactionState.update {
            list
        }
    }

    companion object {
        private const val INITIAL_BALANCE = 550000.0
    }

}