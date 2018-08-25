package com.anthonyminerva.packit

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [indexCard::class], version = 1)
public abstract class indexCardRoomDatabase : RoomDatabase() {

    abstract fun indexCardDao():indexCardDao

    companion object {
        private lateinit var INSTANCE:indexCardRoomDatabase
        internal fun getDatabase(context: Context):indexCardRoomDatabase {
            if (INSTANCE == null)
            {
                synchronized (indexCardRoomDatabase::class.java) {
                    if (INSTANCE == null)
                    {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                indexCardRoomDatabase::class.java, "indexcard_database")
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}