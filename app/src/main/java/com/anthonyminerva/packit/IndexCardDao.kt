package com.anthonyminerva.packit

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface IndexCardDao {

    @Insert
    fun insert(card:indexCard)

    @Query("DELETE FROM indexCards WHERE Pack = :packName")
    fun deletePack(packName:String)

    @Query("SELECT * FROM indexCards WHERE Pack = :packName")
    fun getPack(packName:String):List<indexCard>

    @Query("SELECT * FROM indexCards WHERE Pack = :packName AND Front = :frontName")
    fun getCard(packName:String, frontName:String):indexCard //TODO: Check if fine, or store as List
    
    @Query("SELECT DISTINCT Pack FROM indexCards")
    fun getAllPacks():List<String>

}