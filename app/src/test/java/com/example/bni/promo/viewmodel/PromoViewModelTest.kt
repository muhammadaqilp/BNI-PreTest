package com.example.bni.promo.viewmodel

import com.example.bni.model.PromoModel
import com.example.bni.repository.IPromoRepository
import com.example.bni.ui.main.PromoViewModel
import com.example.bni.ui.main.screen.event.PromoEvent
import com.example.bni.ui.main.state.PromoState
import com.example.bni.util.scheduler.TestSchedulerProvider
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PromoViewModelTest {

    private lateinit var viewModel: PromoViewModel

    @Mock
    private lateinit var promoRepository: IPromoRepository

    private val trampolineScheduler = Schedulers.trampoline()
    private val trampolineSchedulerProvider = TestSchedulerProvider(trampolineScheduler)

    @Before
    fun setup() {
        viewModel = PromoViewModel(promoRepository, trampolineSchedulerProvider)
    }

    @Test
    fun onEvent_GetPromo_Success() {
        val model = listOf(
            PromoModel(
                title = "Test Promo",
                desc = "Test Desc",
                descPromo = "Test Desc Promo",
                location = "Test Location",
                promoName = "Test Promo Name",
                promoCount = 2
            )
        )

        Mockito.`when`(promoRepository.getPromo()).thenReturn(Flowable.just(model))
        viewModel.onEvent(PromoEvent.GetPromo)
        Mockito.verify(promoRepository).getPromo()

        assertEquals(PromoState.Type.SUCCESS, viewModel.promoState.value.type)
        assertEquals(model.size, viewModel.promoState.value.promoData.size)
        assertEquals(model[0].title, viewModel.promoState.value.promoData[0].title)
        assertEquals(model[0].desc, viewModel.promoState.value.promoData[0].desc)
        assertEquals(model[0].descPromo, viewModel.promoState.value.promoData[0].descPromo)
        assertEquals(model[0].location, viewModel.promoState.value.promoData[0].location)
        assertEquals(model[0].promoName, viewModel.promoState.value.promoData[0].promoName)
        assertEquals(model[0].promoCount, viewModel.promoState.value.promoData[0].promoCount)
    }

    @Test
    fun onEvent_GetPromo_Failed() {
        val error = Throwable("Something went wrong")

        Mockito.`when`(promoRepository.getPromo()).thenReturn(Flowable.error(error))
        viewModel.onEvent(PromoEvent.GetPromo)
        Mockito.verify(promoRepository).getPromo()

        assertEquals(PromoState.Type.ERROR, viewModel.promoState.value.type)
        assertEquals(error.message, viewModel.promoState.value.errorMessage)
    }

    @Test
    fun onEvent_RetryGetPromo_Success() {
        val model = listOf(
            PromoModel(
                title = "Test Promo Retry",
                desc = "Test Desc Retry",
                descPromo = "Test Desc Promo Retry",
                location = "Test Location Retry",
                promoName = "Test Promo Name Retry",
                promoCount = 5
            )
        )

        Mockito.`when`(promoRepository.getPromo()).thenReturn(Flowable.just(model))
        viewModel.onEvent(PromoEvent.RetryGetPromo)
        Mockito.verify(promoRepository).getPromo()

        assertEquals(PromoState.Type.SUCCESS, viewModel.promoState.value.type)
        assertEquals(model.size, viewModel.promoState.value.promoData.size)
        assertEquals(model[0].title, viewModel.promoState.value.promoData[0].title)
        assertEquals(model[0].desc, viewModel.promoState.value.promoData[0].desc)
        assertEquals(model[0].descPromo, viewModel.promoState.value.promoData[0].descPromo)
        assertEquals(model[0].location, viewModel.promoState.value.promoData[0].location)
        assertEquals(model[0].promoName, viewModel.promoState.value.promoData[0].promoName)
        assertEquals(model[0].promoCount, viewModel.promoState.value.promoData[0].promoCount)
    }

    @Test
    fun onEvent_RetryGetPromo_Failed() {
        val error = Throwable("Error Unauthorized")

        Mockito.`when`(promoRepository.getPromo()).thenReturn(Flowable.error(error))
        viewModel.onEvent(PromoEvent.RetryGetPromo)
        Mockito.verify(promoRepository).getPromo()

        assertEquals(PromoState.Type.ERROR, viewModel.promoState.value.type)
        assertEquals(error.message, viewModel.promoState.value.errorMessage)
    }

}