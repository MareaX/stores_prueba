package com.cursosandroidant.storesmvvm.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cursosandroidant.storesmvvm.common.entities.StoreEntity

/****
 * Project: Stores
 * From: com.cursosant.android.stores.common.database
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
@Database(entities = arrayOf(StoreEntity::class), version = 2)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}