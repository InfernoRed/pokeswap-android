package com.ccspart2.pokeswap.network.api.ai

import com.ccspart2.pokeswap.data.model.aIInfo.request.AzureAiRequest
import com.ccspart2.pokeswap.data.model.aIInfo.response.AzureAiResponse
import com.ccspart2.pokeswap.network.common.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AzureOpenAiApi {
    @POST(Constants.AZURE_ENDPOINT)
    suspend fun getAiResponse(
        @Body request: AzureAiRequest,
        @Query("api-version") apiVersion: String = Constants.AZURE_API_VERSION,
        @Query("api-key") apiKey: String = Constants.AZURE_API_KEY,
    ): Response<AzureAiResponse>
}
