package com.example.sui.feature.data.datasource

import androidx.room.Dao
import androidx.room.Query
import com.example.sui.feature.domain.model.Sui
import kotlinx.coroutines.flow.Flow

@Dao
interface SuiDao {

    @Query("SELECT * FROM sui")
    fun getAllSui(): Flow<List<Sui>>

    @Query("SELECT * FROM sui WHERE first_name = :firstName")
    fun getSuiByFirstName(firstName: String): Flow<List<Sui>>


}