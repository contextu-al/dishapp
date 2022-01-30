package com.xheghun.dishapp.viewmodel

import androidx.lifecycle.*
import com.xheghun.dishapp.models.database.FavDishRepository
import com.xheghun.dishapp.models.entities.FavDish
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class FavDishViewModel(private val repository: FavDishRepository) : ViewModel() {

    fun insert(dish: FavDish) = viewModelScope.launch {
        repository.insertFavDish(dish)
    }

    val allDishesList: LiveData<List<FavDish>> = repository.allDishesList.asLiveData()

    fun updateDish(dish: FavDish) = viewModelScope.launch { repository.updateFavDish(dish) }

    val favDishesList: LiveData<List<FavDish>> = repository.favDishesList.asLiveData()
}

class FavDishViewModelFactory(private val repository: FavDishRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavDishViewModel::class.java)) {
            return FavDishViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}