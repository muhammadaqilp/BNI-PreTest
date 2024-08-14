package com.example.bni.ui.main.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bni.model.PromoModel
import com.example.bni.ui.main.screen.component.PromoErrorItem
import com.example.bni.ui.main.screen.component.PromoItem
import com.example.bni.ui.main.screen.component.PromoLoadingItem
import com.example.bni.ui.main.state.PromoState

@Composable
fun PromoListScreen(
    state: PromoState = PromoState(),
    onRetry: () -> Unit,
    onClickDetail: (PromoModel) -> Unit
) {
    when (state.type) {
        PromoState.Type.LOADING -> {
            PromoLoadingItem()
        }

        PromoState.Type.SUCCESS -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(state.promoData) { _, item ->
                    PromoItem(
                        data = item,
                        modifier = Modifier.clickable {
                            onClickDetail(item)
                        }
                    )
                }
            }
        }

        PromoState.Type.ERROR -> {
            PromoErrorItem(errorMessage = state.errorMessage, onRetry = onRetry)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PromoListScreenPreview() {
    PromoListScreen(onRetry = {}, onClickDetail = {})
}