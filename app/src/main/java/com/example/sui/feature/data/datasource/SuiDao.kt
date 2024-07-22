package com.example.sui.feature.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sui.feature.domain.model.RelationType
import com.example.sui.feature.domain.model.Sui
import kotlinx.coroutines.flow.Flow

@Dao
interface SuiDao {

    @Query("SELECT * FROM sui WHERE firstName LIKE :nameFilter AND generation = :generationFilter")
    fun getGenealogyByNameAndGeneration(nameFilter: String, generationFilter: Int): Flow<List<Sui>>

    @Query("SELECT * FROM sui WHERE firstName LIKE :nameFilter")
    fun getGenealogyByFirstName(nameFilter: String): Flow<List<Sui>>

    @Query("SELECT * FROM sui WHERE generation = :generationFilter")
    fun getGenealogyByGeneration(generationFilter: Int): Flow<List<Sui>>

    @Query("SELECT * FROM sui")
    fun getAllGenealogy(): Flow<List<Sui>>

    @Insert
    fun insertAll(sui: List<Sui>)

    @Query("SELECT * FROM sui WHERE id = :id")
    suspend fun getSuiById(id: String): Sui?

    @Query("SELECT * FROM sui WHERE idSuperior = :id AND (relationSuperiorType = 'son' OR relationSuperiorType = 'daughter')")
    fun getChildren(id: String): Flow<List<Sui>>

    @Query("SELECT * FROM sui WHERE idSuperior = :id AND relationSuperiorType = :type")
    fun getSpouse(id: String, type: RelationType = RelationType.spouse): Flow<List<Sui>>

    @Query("SELECT * FROM sui WHERE id = (SELECT idSuperior FROM sui WHERE id = :id)")
    suspend fun getFather(id: String): Sui?

    @Query("SELECT * FROM sui WHERE id = (SELECT idMother FROM sui WHERE id = :id)")
    suspend fun getMother(id: String): Sui?

}