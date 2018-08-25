package com.anthonyminerva.packit

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

public class indexCardRepository(application: Application) {

    private val mIndexCardDao:indexCardDao

    init{
        val db:indexCardRoomDatabase = indexCardRoomDatabase.getDatabase(application)
        mIndexCardDao = db.indexCardDao()
    }

    fun getPack(packName:String):LiveData<List<indexCard>>{
        return mIndexCardDao.getPack(packName)
    }

    fun getAllPacks():LiveData<List<String>>{
        return mIndexCardDao.getAllPacks()
    }
    //TODO: Possibly alter to List<indexCard> if necessary
    fun getCard(packName:String, frontName:String):indexCard{
        return mIndexCardDao.getCard(packName, frontName)
    }

    fun search(packName: String, searchTerm:String):LiveData<List<indexCard>>{
        return mIndexCardDao.search(packName, searchTerm)
    }

    fun insert(indexCard:indexCard){
        insertAsyncTask(mIndexCardDao).execute(indexCard)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: indexCardDao) : AsyncTask<indexCard, Void, Void>() {

        override fun doInBackground(vararg params: indexCard): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

}