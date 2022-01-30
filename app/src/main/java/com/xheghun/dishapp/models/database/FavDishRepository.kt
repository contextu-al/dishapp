package com.xheghun.dishapp.models.database

import androidx.annotation.WorkerThread
import com.xheghun.dishapp.models.entities.FavDish

class FavDishRepository(private val favDishDao: FavDishDao) {

    @WorkerThread
    suspend fun insertFavDish(favDish: FavDish) {
        favDishDao.insertFavDishDetails(favDish)
    }

     val allDishesList = favDishDao.getAllDishesList()

    @WorkerThread
    suspend fun updateFavDish(favDish: FavDish) {
        favDishDao.updateFavDish(favDish)
    }

    val favDishesList = favDishDao.getFavDishesList()
}