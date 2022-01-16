package com.xheghun.dishapp.utils

object Constants {
    const val DISH_TYPE = "DishType"
    const val  DISH_CATEGORY = "DishCategory"
    const val DISH_COOKING_TIME = "DishCookingTime"

    const val DISH_IMAGE_SOURCE_LOCAL =  "Local"
    const val DISH_IMAGE_SOURCE_Online =  "Online"

    fun dishTypes() : ArrayList<String> {
        val list = ArrayList<String>()

        list.add("Breakfast")
        list.add("lunch")
        list.add("dinner")
        list.add("dessert")
        list.add("salad")
        list.add("side dish")
        list.add("snacks")
        list.add("others")

        return list
    }


    fun dishCategories() : ArrayList<String> {
        val list = ArrayList<String>()

        list.add("Pizza")
        list.add("BBQ")
        list.add("Bakery")
        list.add("Burger")
        list.add("Cafe")
        list.add("Chicken")
        list.add("Drinks")
        list.add("Sandwich")
        list.add("Tea & Coffee")
        list.add("Wraps")
        list.add("Hot Dogs")

        return list
    }

    fun dishCookingTime() : ArrayList<String> {
        val list = ArrayList<String>()

        list.add("10")
        list.add("15")
        list.add("20")
        list.add("30")
        list.add("35")
        list.add("40")
        list.add("50")
        list.add("60")
        list.add("75")
        list.add("85")
        list.add("120")

        return list
    }

}