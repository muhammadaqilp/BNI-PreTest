package com.example.bni.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bni.R
import com.example.bni.ui.main.navigation.PromoDetail
import com.example.bni.ui.main.navigation.PromoList
import com.example.bni.ui.main.screen.PromoDetailScreen
import com.example.bni.ui.main.screen.PromoListScreen
import com.example.bni.ui.main.screen.component.TopAppBar
import com.example.bni.ui.main.screen.event.PromoEvent

@Composable
fun PromoApp(
    viewModel: PromoViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

    val promoState by viewModel.promoState.collectAsState()
    var toolbarTitle by remember { mutableIntStateOf(R.string.promo_list) }

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(PromoEvent.GetPromo)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = stringResource(id = toolbarTitle),
            canNavigateBack = navController.previousBackStackEntry != null,
            onFinish = {
                navController.navigateUp()
            }
        )
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PromoList,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<PromoList> {
                toolbarTitle = R.string.promo_list
                PromoListScreen(
                    state = promoState,
                    onRetry = { viewModel.onEvent(PromoEvent.RetryGetPromo) },
                    onClickDetail = { model ->
                        navController.navigate(PromoDetail(data = model))
                    }
                )
            }

            composable<PromoDetail>(
                typeMap = PromoDetail.typeMap
            ) { backStackEntry ->
                toolbarTitle = R.string.promo_detail
                val data = backStackEntry.toRoute<PromoDetail>()
                PromoDetailScreen(data = data.data)
            }
        }
    }
}