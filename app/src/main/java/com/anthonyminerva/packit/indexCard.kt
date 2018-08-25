package com.anthonyminerva.packit

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.util.TableInfo
import android.support.annotation.NonNull

@Entity(tableName = "indexCards", primaryKeys = arrayOf("Front", "Pack"))
public class indexCard(front:String, back:String, pack:String) {

    @NonNull
    @ColumnInfo(name = "Front")
    private var frontCard:String
    @NonNull
    @ColumnInfo(name = "Back")
    private var backCard:String
    @NonNull
    @ColumnInfo(name = "Pack")
    private var packName:String

    init {
        frontCard = front
        backCard = back
        packName = pack
    }

    fun getCardFront():String {
        return frontCard;
    }

    fun setCardFront(front:String) {
        frontCard = front
    }

    fun getCardBack():String {
        return backCard;
    }

    fun setCardBack(back:String) {
        backCard = back
    }

    fun getPackName():String {
        return packName
    }

    fun setPackName(pack:String) {
        packName = pack
    }

}