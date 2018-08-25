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

    @Query("DELETE FROM indexCards WHERE Pack = :packName")
    fun deletePack(packName:String)

    @Query("SELECT * FROM indexCards WHERE Pack = :packName")
    fun getPack(packName:String): LiveData<List<indexCard>>

    @Query("SELECT * FROM indexCards WHERE Pack = :packName AND Front = :frontName")
    fun getCard(packName:String, frontName:String):indexCard //TODO: Check if fine, or store as List

    @Query("SELECT DISTINCT Pack FROM indexCards")
    fun getAllPacks():LiveData<List<String>>

    @Query("SELECT * FROM indexCards WHERE Pack = :packName AND (Front LIKE '%'+:searchTerm+'%' OR Back LIKE '%'+:searchTerm+'%')")
    fun search(packName:String, searchTerm:String):LiveData<List<indexCard>>

}