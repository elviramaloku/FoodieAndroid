package com.example.foodie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); showScreen() }
    private fun showScreen() {
        val root = Ui.root(this,"Welcome to Foodie 🍽")
        val content = Ui.scroll(this,root)
        content.addView(Ui.label(this,"Bon Appétit!",19f,true))
        val top = Ui.row(this)
        val search = EditText(this).apply { hint="Search Food..."; setSingleLine(true); setTextColor(Ui.white); setHintTextColor(Ui.white); background=Ui.shape(Ui.card,12,this@MainActivity) }
        Ui.pad(search,this,12)
        top.addView(search,LinearLayout.LayoutParams(0,Ui.dp(this,54),1f))
        top.addView(Ui.button(this,"🛒 Cart") { startActivity(Intent(this,CartActivity::class.java)) })
        content.addView(top)
        val results = Ui.column(this)
        content.addView(results)
        val standard = Ui.column(this)
        content.addView(standard)
        Ui.section(this,"Today's Best Food",standard)
        FoodRepository.foods.sortedByDescending { it.rating }.take(5).forEach { food ->
            Ui.foodCard(this,food,standard) { Ui.openDetail(this,food.id) }
        }
        Ui.section(this,"Category",standard)
        FoodRepository.categories.forEach { category ->
            val b=Ui.button(this,"${FoodRepository.foods.first { it.category==category }.emoji}  $category") {
                startActivity(Intent(this,ListFoodActivity::class.java).putExtra("category",category))
            }
            val params=LinearLayout.LayoutParams(-1,Ui.dp(this,55)).apply { bottomMargin=Ui.dp(this@MainActivity,8) }
            standard.addView(b,params)
        }
        search.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?,start:Int,count:Int,after:Int) {}
            override fun onTextChanged(s: CharSequence?,start:Int,before:Int,count:Int) {
                val q=s.toString().trim()
                results.removeAllViews()
                standard.visibility=if(q.isBlank()) android.view.View.VISIBLE else android.view.View.GONE
                if(q.isNotBlank()) {
                    Ui.section(this@MainActivity,"Search results",results)
                    val matches=FoodRepository.foods.filter { it.name.contains(q,true)||it.category.contains(q,true) }
                    if(matches.isEmpty()) results.addView(Ui.label(this@MainActivity,"No food found."))
                    matches.forEach { food -> Ui.foodCard(this@MainActivity,food,results) { Ui.openDetail(this@MainActivity,food.id) } }
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
