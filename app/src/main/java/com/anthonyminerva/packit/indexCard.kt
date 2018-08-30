package com.anthonyminerva.packit

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.util.TableInfo
import android.support.annotation.NonNull

@Entity(tableName = "cards", primaryKeys = arrayOf("front_card", "pack_name"))
data class indexCard(var front: String, var back: String, var pack:String) {

    @NonNull
    @ColumnInfo(name = "front_card")
    public var frontCard:String? = ""
    @NonNull
    @ColumnInfo(name = "back_card")
    public var backCard:String? = ""
    @NonNull
    @ColumnInfo(name = "pack_name")
    public var packName:String? = ""

    init {
        frontCard = front
        backCard = back
        packName = pack
    }

}