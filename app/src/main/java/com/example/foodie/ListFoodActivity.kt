package com.example.foodie

import android.app.Activity
import android.os.Bundle

class ListFoodActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val category=intent.getStringExtra("category") ?: "Pizza"
        val root=Ui.root(this,category,true)
        val content=Ui.scroll(this,root)
        FoodRepository.foods.filter { it.category==category }.forEach { food ->
            Ui.foodCard(this,food,content) { Ui.openDetail(this,food.id) }
        }
    }
}
