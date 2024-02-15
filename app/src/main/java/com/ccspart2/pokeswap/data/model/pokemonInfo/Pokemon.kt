package com.ccspart2.pokeswap.data.model.pokemonInfo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey var id: String = "",
    @Embedded var images: Images? = null,
    var name: String = "",
    var rarity: String = "",
    var flavorText: String = "",
    var evolvesFrom: String? = null,
    @Embedded var cardmarket: CardmarketInfo = CardmarketInfo(),
    var supertype: String = "",
    @Ignore var types: List<String> = listOf(),
    @Ignore var subtypes: List<String> = listOf(),
    var level: String = "",
    var hp: String = "",
    @Ignore var abilities: List<Ability> = listOf(),
    @Ignore var attacks: List<Attack> = listOf(),
    @Ignore var weaknesses: MutableList<Weakness> = mutableListOf(),
    @Ignore var resistances: MutableList<Resistance> = mutableListOf(),
    @Ignore var retreatCost: MutableList<String> = mutableListOf(),
    var convertedRetreatCost: Int = 0,
    @Ignore var set: SetInfo = SetInfo(),
    var number: String = "",
    var artist: String = "",
    @Ignore var nationalPokedexNumbers: MutableList<Int> = mutableListOf(),
    @Ignore var tcgplayer: TcgPlayerDetails = TcgPlayerDetails(),

) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
        )
        return matchingCombinations.all {
            it.contains(query, ignoreCase = true)
        }
    }
}
