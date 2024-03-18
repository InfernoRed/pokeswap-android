package com.ccspart2.pokeswap.network.repo

import com.ccspart2.pokeswap.data.model.aIInfo.request.AzureAiRequest
import com.ccspart2.pokeswap.data.model.aIInfo.response.AzureAiResponse
import com.ccspart2.pokeswap.network.api.ai.AzureOpenAiServices
import com.ccspart2.pokeswap.utils.LogUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AzureAiRepository @Inject constructor(
    private val services: AzureOpenAiServices,
) {
    suspend fun getCardAiInfo(azureAiRequest: AzureAiRequest): Flow<AzureAiResponse?> = flow {
        try {
            services.postAIRequest(azureAiRequest)?.let {
                emit(it)
            }
        } catch (e: Exception) {
            LogUtils.error("Network Error: ${e.message}")
        }
    }
}
