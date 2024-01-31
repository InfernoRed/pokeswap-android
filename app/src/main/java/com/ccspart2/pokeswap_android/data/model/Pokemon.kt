package com.ccspart2.pokeswap_android.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey var id: String = "",
    @Embedded var images: Images? = null,
    var name: String = "",
)
