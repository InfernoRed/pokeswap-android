package com.ccspart2.pokeswap_android.data.model.pokemonInfo

data class Attack(
    var name: String = "",
    var cost: MutableList<String> = mutableListOf(),
    var convertedEnergyCost: Int = 0,
    var damage: String = "",
    var text: String = "",
)
