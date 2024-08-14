package com.example.bni.ui.main

import androidx.lifecycle.ViewModel
import com.example.bni.repository.PromoRepository
import com.example.bni.ui.main.screen.event.PromoEvent
import com.example.bni.ui.main.state.PromoState
import com.example.bni.util.disposedBy
import com.example.bni.util.scheduler.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PromoViewModel @Inject constructor(
    private val promoRepository: PromoRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private var disposable = CompositeDisposable()

    private val _promoState = MutableStateFlow(PromoState())
    val promoState: StateFlow<PromoState> = _promoState

    fun onEvent(event: PromoEvent) {
        when (event) {
            PromoEvent.GetPromo -> {
                getPromo()
            }

            PromoEvent.RetryGetPromo -> {
                retryGetPromo()
            }
        }
    }

    private fun getPromo() {
        promoRepository.getPromo()
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                _promoState.update { currentState ->
                    currentState.copy(
                        promoData = it,
                        type = PromoState.Type.SUCCESS
                    )
                }
            }, {
                _promoState.update { currentState ->
                    currentState.copy(
                        promoData = listOf(),
                        type = PromoState.Type.ERROR,
                        errorMessage = it.message.orEmpty()
                    )
                }
            }).disposedBy(disposable)
    }

    private fun retryGetPromo() {
        resetState()
        getPromo()
    }

    private fun resetState() {
        _promoState.update { PromoState() }
    }

}