package com.ccspart2.pokeswap.data.model.pokemonInfo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "pokemon")
@JsonClass(generateAdapter = true)
data class Pokemon(
    @field:Json(name = "id") @PrimaryKey var id: String = "",
    @field:Json(name = "images") @Embedded var images: Images? = null,
    @field:Json(name = "name") var name: String = "",
    @field:Json(name = "rarity") var rarity: String = "",
    @field:Json(name = "flavorText") var flavorText: String = "",
    @field:Json(name = "evolvesFrom") var evolvesFrom: String? = null,
    @field:Json(name = "cardmarket") @Embedded var cardmarket: Cardmarket = Cardmarket(),
    @field:Json(name = "supertype") var supertype: String = "",
    @field:Json(name = "types") @Ignore var types: List<String> = listOf(),
    @field:Json(name = "subtypes") @Ignore var subtypes: List<String> = listOf(),
    @field:Json(name = "level") var level: String = "",
    @field:Json(name = "hp") var hp: String = "",
    @field:Json(name = "abilities") @Ignore var abilities: List<Ability> = listOf(),
    @field:Json(name = "attacks") @Ignore var attacks: List<Attack> = listOf(),
    @field:Json(name = "weaknesses") @Ignore var weaknesses: MutableList<Weaknesse> = mutableListOf(),
    @field:Json(name = "resistances") @Ignore var resistances: MutableList<Resistance> = mutableListOf(),
    @field:Json(name = "retreatCost") @Ignore var retreatCost: MutableList<String> = mutableListOf(),
    @field:Json(name = "convertedRetreatCost") var convertedRetreatCost: Int = 0,
    @field:Json(name = "set") @Ignore var set: Set = Set(),
    @field:Json(name = "number") var number: String = "",
    @field:Json(name = "artist") var artist: String = "",
    @field:Json(name = "nationalPokedexNumbers") @Ignore var nationalPokedexNumbers: MutableList<Int> = mutableListOf(),
    @field:Json(name = "tcgplayer") @Ignore var tcgplayer: Tcgplayer = Tcgplayer(),
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
