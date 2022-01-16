package com.xheghun.dishapp.application

import android.app.Application
import com.xheghun.dishapp.models.database.FavDishDatabase
import com.xheghun.dishapp.models.database.FavDishRepository

class FavDishApplication: Application() {
    private val database by lazy { FavDishDatabase.getDatabase(this) }

    val repository by lazy { FavDishRepository(database.favDishDao()) }
}