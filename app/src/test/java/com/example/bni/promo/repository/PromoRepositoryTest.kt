package com.example.bni.promo.repository

import com.example.bni.model.PromoModel
import com.example.bni.network.service.PromoApi
import com.example.bni.promo.utils.PromoRepositoryDummyData
import com.example.bni.repository.IPromoRepository
import com.example.bni.repository.PromoRepository
import io.reactivex.Flowable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PromoRepositoryTest {

    @Mock
    private lateinit var promoApi: PromoApi

    private lateinit var promoRepository: IPromoRepository

    @Before
    fun setup() {
        promoRepository = PromoRepository(promoApi)
    }

    @Test
    fun getPromo_SuccessGetPromo_GiveValidResponse() {
        val dummyResponse = PromoRepositoryDummyData.getPromoResponse()
        val mappedResponse = PromoModel.Mapper().apply(dummyResponse)

        `when`(promoApi.getListPromo()).thenReturn(Flowable.just(dummyResponse))
        promoRepository.getPromo()
        verify(promoApi).getListPromo()

        val response = promoRepository.getPromo()
        response.test().assertComplete()
        response.test().assertNoErrors()

        val result = response.test().values()[0]
        assertEquals(mappedResponse, result)

        verify(promoApi, atLeastOnce()).getListPromo()
    }

    @Test
    fun getPromo_FailedGetPromo_GiveErrorResponse() {
        val dummyResponse = PromoRepositoryDummyData.getPromoErrorResponse()
        val exception = Throwable(dummyResponse.error?.message)

        `when`(promoApi.getListPromo()).thenReturn(Flowable.error(exception))

        val response = promoRepository.getPromo()
        response.test().assertNotComplete()
        response.test().assertError(exception)

        verify(promoApi, atLeastOnce()).getListPromo()
    }

}