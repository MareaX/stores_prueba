package com.cursosandroidant.storesmvvm.mainModule.model

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.cursosandroidant.storesmvvm.StoreApplication
import com.cursosandroidant.storesmvvm.common.entities.StoreEntity
import com.cursosandroidant.storesmvvm.common.utils.Constants.ERROR
import com.cursosandroidant.storesmvvm.common.utils.Constants.GET_ALL_PATH
import com.cursosandroidant.storesmvvm.common.utils.Constants.STATUS_PROPERTY
import com.cursosandroidant.storesmvvm.common.utils.Constants.STORES_PROPERTY
import com.cursosandroidant.storesmvvm.common.utils.Constants.STORES_URL
import com.cursosandroidant.storesmvvm.common.utils.Constants.SUCCESS
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.LinkedBlockingQueue

/****
 * Project: Stores
 * From: com.cursosant.android.stores.mainModule.model
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
class MainInteractor {

    fun getStores(callback: (MutableList<StoreEntity>) -> Unit) {
        val url = "$STORES_URL$GET_ALL_PATH"
        var storeList = mutableListOf<StoreEntity>()
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val status = response.optInt(STATUS_PROPERTY, ERROR)

                if (status == SUCCESS) {
                    val jsonList = response.optJSONArray(STORES_PROPERTY)?.toString()
                    jsonList.let {
                        val mutableListType = object : TypeToken<MutableList<StoreEntity>>(){}.type
                        storeList = Gson().fromJson(jsonList, mutableListType)
                    }
                }
                callback(storeList)
            },
            {
                it.printStackTrace()
                callback(storeList)
            })
        StoreApplication.storeApi.addToRequestQueue(jsonObjectRequest)
    }

    fun getStoresRoom(callback: (MutableList<StoreEntity>) -> Unit) {
        val queue = LinkedBlockingQueue<MutableList<StoreEntity>>()
        Thread {
            val storeList = StoreApplication.database.storeDao().getAllStores()
            val json = Gson().toJson(storeList)
            Log.i("Gson", json)
            queue.add(storeList)
        }.start()
        callback(queue.take())
    }

    fun deleteStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit) {
        val queue = LinkedBlockingQueue<StoreEntity>()
        Thread {
            StoreApplication.database.storeDao().deleteStore(storeEntity)
            queue.add(storeEntity)
        }.start()
        callback(queue.take())
    }

    fun updateStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit) {
        val queue = LinkedBlockingQueue<StoreEntity>()
        Thread {
            StoreApplication.database.storeDao().updateStore(storeEntity)
            queue.add(storeEntity)
        }.start()
        callback(queue.take())
    }
}