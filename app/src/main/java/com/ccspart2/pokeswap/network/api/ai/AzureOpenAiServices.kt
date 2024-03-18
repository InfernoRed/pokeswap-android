package com.ccspart2.pokeswap.network.api.ai

import com.ccspart2.pokeswap.data.model.aIInfo.request.AzureAiRequest
import com.ccspart2.pokeswap.data.model.aIInfo.response.AzureAiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AzureOpenAiServices @Inject constructor(
    private val azureOpenAiApi: AzureOpenAiApi,
) {
    suspend fun postAIRequest(azureAiRequest: AzureAiRequest): AzureAiResponse? {
        return withContext(Dispatchers.IO) {
            val response = azureOpenAiApi.getAiResponse(azureAiRequest)
            response.body()
        }
    }
}
