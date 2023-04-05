package com.cursosandroidant.storesmvvm.editModule.model

import com.cursosandroidant.storesmvvm.StoreApplication
import com.cursosandroidant.storesmvvm.common.entities.StoreEntity
import java.util.concurrent.LinkedBlockingQueue

/****
 * Project: Stores
 * From: com.cursosant.android.stores.editModule.model
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
class EditStoreInteractor {

    fun saveStore(storeEntity: StoreEntity, callback: (Long) -> Unit){
        val queue = LinkedBlockingQueue<Long>()
        Thread{
            val newId = StoreApplication.database.storeDao().addStore(storeEntity)
            StoreApplication.database.storeDao().deleteStore(storeEntity)
            queue.add(newId)
        }.start()
        callback(queue.take())
    }

    fun updateStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){
        val queue = LinkedBlockingQueue<StoreEntity>()
        Thread{
            StoreApplication.database.storeDao().updateStore(storeEntity)
            queue.add(storeEntity)
        }.start()
        callback(queue.take())
    }
}