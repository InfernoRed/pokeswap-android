package com.ccspart2.pokeswap.network.common

class Constants {
    companion object {
        const val POKEMON_BASE_URL = "https://api.pokemontcg.io/v2/"
        const val POKEMON_ENDPOINT = "https://api.pokemontcg.io/v2/cards/"

        const val EXCHANGE_RATE_BASE_URL = "https://v6.exchangerate-api.com/v6/"
        const val EXCHANGE_RATE_ENDPOINT =
            "https://v6.exchangerate-api.com/v6/54264b06cb36f602b081c45f/latest/EUR/"

        const val AZURE_BASE_URL = "https://irt-cardlabel-poc-west.openai.azure.com/openai/"
        const val AZURE_ENDPOINT = "deployments/PokeSwap/chat/completions"
        const val AZURE_API_KEY = "e578b80bd5754fffb69574319d609c6f"
        const val AZURE_API_VERSION = "2024-02-15-preview"
    }
}
