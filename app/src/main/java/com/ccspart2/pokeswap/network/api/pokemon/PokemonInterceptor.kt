package com.ccspart2.pokeswap.network.api.pokemon

import com.ccspart2.pokeswap.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class PokemonInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", BuildConfig.apiKey)
            .build()
        return chain.proceed(request)
    }
}
