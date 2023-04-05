package com.cursosandroidant.storesmvvm.common.database

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class StoreApi(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: StoreApi? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: StoreApi(context).also {
                INSTANCE = it
            }
        }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }


    fun <T> addToRequestQueue(req: Request<T>){
        requestQueue.add(req)
    }
}