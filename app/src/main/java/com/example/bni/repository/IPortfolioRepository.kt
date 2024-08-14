package com.example.bni.repository

import com.example.bni.model.PortfolioModel

interface IPortfolioRepository {
    fun getPortfolioData(): PortfolioModel
}