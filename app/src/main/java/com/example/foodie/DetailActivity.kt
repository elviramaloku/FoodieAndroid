package com.example.foodie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.*

class DetailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val food=FoodRepository.find(intent.getIntExtra("food_id",-1)) ?: run { finish(); return }
        val root=Ui.root(this,"Food Details",true)
        val content=Ui.scroll(this,root)
        content.addView(Ui.label(this,food.emoji,100f).apply { gravity=Gravity.CENTER })
        content.addView(Ui.label(this,food.name,25f,true))
        content.addView(Ui.label(this,"★ ${food.rating}     ◷ ${food.minutes} min",17f))
        Ui.section(this,"Details",content)
        content.addView(Ui.label(this,food.description,17f))
        Ui.section(this,"Quantity",content)
        val qtyRow=Ui.row(this)
        var quantity=1
        val number=Ui.label(this,"1",23f,true).apply { gravity=Gravity.CENTER }
        qtyRow.addView(Ui.button(this,"−") { if(quantity>1) { quantity--; number.text=quantity.toString() } })
        qtyRow.addView(number,LinearLayout.LayoutParams(Ui.dp(this,65),-2))
        qtyRow.addView(Ui.button(this,"+") { quantity++; number.text=quantity.toString() })
        content.addView(qtyRow)
        val total=Ui.label(this,"Total: ${Ui.money(food.price)}",23f,true)
        content.addView(total)
        // Keep total in sync with the quantity selector.
        val update= { total.text="Total: ${Ui.money(food.price*quantity)}" }
        qtyRow.getChildAt(0).setOnClickListener { if(quantity>1) quantity--; number.text=quantity.toString(); update() }
        qtyRow.getChildAt(2).setOnClickListener { quantity++; number.text=quantity.toString(); update() }
        root.addView(Ui.button(this,"Add To Cart 🛒") {
            CartStore.add(this,food.id,quantity)
            Toast.makeText(this,"Added to cart",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,CartActivity::class.java))
        },LinearLayout.LayoutParams(-1,Ui.dp(this,60)))
    }
}
