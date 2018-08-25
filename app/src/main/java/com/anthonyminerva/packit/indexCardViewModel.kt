package com.anthonyminerva.packit

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class indexCardViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository: indexCardRepository

    init {
        mRepository = indexCardRepository(application)
    }

    fun getPack(packName:String): LiveData<List<indexCard>>{
        return mRepository.getPack(packName)
    }

    fun getAllPacks():LiveData<List<String>>{
        return mRepository.getAllPacks()
    }
    //TODO: Possibly change to List if needed?
    fun getCard(packName:String, frontName:String):indexCard{
        return mRepository.getCard(packName, frontName)
    }

    fun search(packName:String, searchTerm:String):LiveData<List<indexCard>>{
        return mRepository.search(packName, searchTerm)
    }

    fun insert(indexCard:indexCard){
        mRepository.insert(indexCard)
    }

}