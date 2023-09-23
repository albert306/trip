package de.awolf.trip_compose.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stop(
    @PrimaryKey
    val id: String,
    val name: String,
    val region: String = "Dresden",
    var isFavourite: Boolean = false,
)