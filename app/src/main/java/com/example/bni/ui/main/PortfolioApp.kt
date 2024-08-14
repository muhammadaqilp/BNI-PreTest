package com.example.bni.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bni.ui.main.navigation.PortfolioChart
import com.example.bni.ui.main.navigation.PortfolioDetail
import com.example.bni.ui.main.screen.PortfolioChartScreen
import com.example.bni.ui.main.screen.PortfolioDetailScreen
import com.example.bni.ui.main.screen.PortfolioEvent

@Composable
fun PortfolioApp(
    viewModel: PortfolioViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

    val portfolioState by viewModel.portfolioState.collectAsState()
    val portfolioDetailState by viewModel.portfolioDetailState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(PortfolioEvent.InitializeData)
    }

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PortfolioChart,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<PortfolioChart> {
                PortfolioChartScreen(
                    state = portfolioState,
                    onClickDetail = {
                        viewModel.onEvent(PortfolioEvent.ClickDetail(category = it))
                        navController.navigate(PortfolioDetail)
                    }
                )
            }

            composable<PortfolioDetail> {
                PortfolioDetailScreen(portfolioDetailState)
            }
        }
    }

}