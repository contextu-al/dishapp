package com.xheghun.dishapp.models.database

import androidx.room.Dao
import androidx.room.Insert
import com.xheghun.dishapp.models.entities.FavDish

@Dao
interface FavDishDao {

    @Insert
    suspend fun insertFavDishDetails(favDish: FavDish)
}