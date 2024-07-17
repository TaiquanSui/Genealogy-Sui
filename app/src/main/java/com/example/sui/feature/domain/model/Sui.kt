package com.example.sui.feature.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sui")
data class Sui(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val generation: Int,
    val relation: Int,
    val relationType: RelationType,
    val rank: Int,
    val introduction: String,
    val note: String

)
