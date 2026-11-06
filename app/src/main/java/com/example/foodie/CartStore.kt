package com.example.foodie

import android.content.Context

object CartStore {
    private const val PREFS = "foodie_cart"
    private fun prefs(context: Context) = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    fun quantity(context: Context, id: Int): Int = prefs(context).getInt("food_$id", 0)
    fun setQuantity(context: Context, id: Int, count: Int) {
        prefs(context).edit().putInt("food_$id", count.coerceAtLeast(0)).apply()
    }
    fun add(context: Context, id: Int, amount: Int = 1) = setQuantity(context, id, quantity(context, id) + amount)
    fun items(context: Context): List<Pair<Food, Int>> = FoodRepository.foods.mapNotNull {
        val n = quantity(context, it.id)
        if (n > 0) it to n else null
    }
    fun clear(context: Context) = prefs(context).edit().clear().apply()
}
