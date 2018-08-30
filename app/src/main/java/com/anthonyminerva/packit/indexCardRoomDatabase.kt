package com.anthonyminerva.packit

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [indexCard::class], version = 1)
public abstract class indexCardRoomDatabase : RoomDatabase() {

    abstract fun indexCardDao():indexCardDao

    companion object {
        private var INSTANCE: indexCardRoomDatabase? = null

        fun getDatabase(context: Context): indexCardRoomDatabase {
            if(INSTANCE == null){
                synchronized(indexCardRoomDatabase::class.java) {
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder((context.applicationContext), indexCardRoomDatabase::class.java, "packit").build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

}