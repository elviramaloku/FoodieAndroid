package com.example.foodie

import android.app.Activity
import android.os.Bundle
import android.app.AlertDialog
import android.widget.*

class CartActivity : Activity() {
    private var couponApplied=false
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); showCart() }
    private fun showCart() {
        val root=Ui.root(this,"Cart",true)
        val content=Ui.scroll(this,root)
        val items=CartStore.items(this)
        if(items.isEmpty()) { content.addView(Ui.label(this,"Your Cart Is Empty",22f,true)); return }
        items.forEach { (food, count) ->
            Ui.foodCard(this,food,content) { Ui.openDetail(this,food.id) }
            val row=Ui.row(this)
            row.addView(Ui.button(this,"−") { CartStore.setQuantity(this,food.id,count-1); showCart() })
            row.addView(Ui.label(this,"  $count  ",20f,true))
            row.addView(Ui.button(this,"+") { CartStore.setQuantity(this,food.id,count+1); showCart() })
            row.addView(Ui.label(this,"   ${Ui.money(food.price*count)}",17f,true))
            content.addView(row)
        }
        Ui.section(this,"Discount code",content)
        val code=EditText(this).apply { hint="Enter Your Code"; setSingleLine(true) }
        content.addView(code)
        content.addView(Ui.button(this,"Apply Coupon") {
            couponApplied=code.text.toString().trim().equals("17righthere",true)
            Toast.makeText(this,if(couponApplied) "Discount applied!" else "Invalid code",Toast.LENGTH_SHORT).show()
            if(couponApplied) showCart()
        })
        Ui.section(this,"Order Summary",content)
        val subtotal=items.sumOf { (food,n) -> food.price*n }
        val delivery=2.99
        val tax=subtotal*.08
        val discount=if(couponApplied) subtotal*.17 else 0.0
        val total=subtotal+delivery+tax-discount
        content.addView(Ui.label(this,"Subtotal: ${Ui.money(subtotal)}"))
        content.addView(Ui.label(this,"Delivery: ${Ui.money(delivery)}"))
        content.addView(Ui.label(this,"Total Tax (8%): ${Ui.money(tax)}"))
        if(couponApplied) content.addView(Ui.label(this,"Coupon (17% off): −${Ui.money(discount)}"))
        content.addView(Ui.label(this,"Total: ${Ui.money(total)}",23f,true))
        content.addView(Ui.button(this,"Place Order") {
            AlertDialog.Builder(this).setTitle("Order placed!")
                .setMessage("This demo has recorded your order locally. No payment or restaurant delivery was made.")
                .setPositiveButton("OK") { _,_ -> CartStore.clear(this); couponApplied=false; showCart() }
                .show()
        },LinearLayout.LayoutParams(-1,Ui.dp(this,60)))
    }
}
