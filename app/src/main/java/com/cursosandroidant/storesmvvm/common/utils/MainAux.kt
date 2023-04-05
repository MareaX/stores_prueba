package com.cursosandroidant.storesmvvm.common.utils

import com.cursosandroidant.storesmvvm.common.entities.StoreEntity

/****
 * Project: Stores
 * From: com.cursosant.android.storesmvvm.common.utils
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
interface MainAux {
    fun hideFab(isVisible: Boolean = false)

    fun addStore(storeEntity: StoreEntity)
    fun updateStore(storeEntity: StoreEntity)
}