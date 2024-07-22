package com.example.sui.feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sui")
data class Sui(

    val generation: Int,
    @PrimaryKey(autoGenerate = false) val id: String,
    val firstName: String,
    val idSuperior: String,
    val idMother: String,
    val relationSuperiorType: RelationType,
    val rank: Int,
    val residencePlace: String,
    val birthday: String,
    val death: String,
    val noteOfDeath: String,
    val burialPlace: String,
    val alias: String,
    val profession: String,
    val personalInformation: String,
    val introduction: String,
    val note: String

)
