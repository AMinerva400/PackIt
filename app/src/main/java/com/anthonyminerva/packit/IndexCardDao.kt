package com.anthonyminerva.packit

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface indexCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(card:indexCard)

    @Query("DELETE FROM cards WHERE pack_name = :packName")
    fun deletePack(packName:String)

    @Query("SELECT * FROM cards WHERE pack_name = :packName")
    fun getPack(packName:String): LiveData<List<indexCard>>

    @Query("SELECT * FROM cards WHERE pack_name = :packName AND front_card = :frontName")
    fun getCard(packName:String, frontName:String):indexCard //TODO: Check if fine, or store as List

    @Query("SELECT DISTINCT pack_name FROM cards")
    fun getAllPacks():LiveData<List<String>>

    @Query("SELECT * FROM cards WHERE pack_name = :packName AND (front_card LIKE '%'+:searchTerm+'%' OR back_card LIKE '%'+:searchTerm+'%')")
    fun search(packName:String, searchTerm:String):LiveData<List<indexCard>>

}