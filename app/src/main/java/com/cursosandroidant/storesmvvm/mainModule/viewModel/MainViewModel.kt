package com.cursosandroidant.storesmvvm.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursosandroidant.storesmvvm.common.entities.StoreEntity
import com.cursosandroidant.storesmvvm.mainModule.model.MainInteractor

/****
 * Project: Stores
 * From: com.cursosant.android.stores.mainModule.viewModel
 * Created by Alain Nicolás Tello on 01/02/23 at 17:52
 * All rights reserved 2023.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
class MainViewModel: ViewModel() {
    private var storeList: MutableList<StoreEntity>
    private var interactor: MainInteractor

    init {
        storeList = mutableListOf()
        interactor = MainInteractor()
    }

    private val stores: MutableLiveData<List<StoreEntity>> by lazy {
        MutableLiveData<List<StoreEntity>>()/*.also { //descomentar con corrutinas
            loadStores()
        }*/
    }

    fun getStores(): LiveData<List<StoreEntity>>{
        return stores.also { loadStores() }
    }

    private fun loadStores(){
        interactor.getStores {
            stores.value = it
            storeList = it
        }
    }

    fun deleteStore(storeEntity: StoreEntity){
        interactor.deleteStore(storeEntity) {
            val index = storeList.indexOf(storeEntity)
            if (index != -1) {
                storeList.removeAt(index)
                stores.value = storeList
            }
        }
    }

    fun updateStore(storeEntity: StoreEntity){
        storeEntity.isFavorite = !storeEntity.isFavorite
        interactor.updateStore(storeEntity) {
            val index = storeList.indexOf(storeEntity)
            if (index != -1) {
                storeList.set(index, storeEntity)
                stores.value = storeList
            }
        }
    }
}