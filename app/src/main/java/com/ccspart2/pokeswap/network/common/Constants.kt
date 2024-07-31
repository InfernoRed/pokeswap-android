package com.ccspart2.pokeswap.network.common

class Constants {
    companion object {
        const val POKEMON_BASE_URL = "https://api.pokemontcg.io/v2/"
        const val POKEMON_ENDPOINT = "https://api.pokemontcg.io/v2/cards/"

        const val EXCHANGE_RATE_BASE_URL = "https://v6.exchangerate-api.com/v6/"
        const val EXCHANGE_RATE_ENDPOINT =
            "https://v6.exchangerate-api.com/v6/54264b06cb36f602b081c45f/latest/EUR/"

        const val AZURE_BASE_URL = "https://irt-pokeswap.openai.azure.com/"
        const val AZURE_ENDPOINT = "openai/deployments/pokeSwap/chat/completions"
        const val AZURE_API_VERSION = "2024-02-15-preview"
    }
}
