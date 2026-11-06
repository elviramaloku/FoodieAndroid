package com.example.foodie

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.*

object Ui {
    val bg = Color.rgb(209, 185, 144)
    val card = Color.rgb(174, 148, 109)
    val orange = Color.rgb(255, 168, 0)
    val white = Color.WHITE
    fun dp(c: Context, v: Int): Int = (v * c.resources.displayMetrics.density + .5f).toInt()
    fun shape(color: Int, radius: Int, c: Context) = GradientDrawable().apply {
        setColor(color); cornerRadius = dp(c, radius).toFloat()
    }
    fun column(c: Context) = LinearLayout(c).apply { orientation = LinearLayout.VERTICAL }
    fun row(c: Context) = LinearLayout(c).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL }
    fun label(c: Context, value: String, sp: Float = 16f, bold: Boolean = false, color: Int = white) = TextView(c).apply {
        text = value; textSize = sp; setTextColor(color); if (bold) setTypeface(null, Typeface.BOLD)
    }
    fun pad(v: View, c: Context, n: Int = 16) { v.setPadding(dp(c,n),dp(c,n),dp(c,n),dp(c,n)) }
    fun button(c: Context, value: String, click: () -> Unit) = Button(c).apply {
        text = value; isAllCaps = false; setTextColor(Color.BLACK)
        background = shape(orange, 18, c)
        setOnClickListener { click() }
    }
    fun root(a: Activity, title: String, back: Boolean = false): LinearLayout {
        val outer = column(a).apply { setBackgroundColor(bg) }
        val header = row(a); pad(header, a, 12)
        if (back) {
            header.addView(button(a, "‹") { a.finish() }, LinearLayout.LayoutParams(dp(a,56),dp(a,48)))
        }
        val heading = label(a, title, 25f, true)
        heading.gravity = Gravity.CENTER_VERTICAL
        header.addView(heading, LinearLayout.LayoutParams(0,dp(a,52),1f))
        outer.addView(header)
        a.setContentView(outer)
        return outer
    }
    fun scroll(a: Activity, parent: LinearLayout): LinearLayout {
        val sc = ScrollView(a).apply { isFillViewport = true }
        parent.addView(sc, LinearLayout.LayoutParams(-1,0,1f))
        val col = column(a); pad(col,a,12); sc.addView(col)
        return col
    }
    fun foodCard(a: Activity, food: Food, parent: LinearLayout, onClick: () -> Unit) {
        val r = row(a).apply { background = shape(card,18,a) }; pad(r,a,12)
        val emoji = label(a,food.emoji,40f); r.addView(emoji,LinearLayout.LayoutParams(dp(a,76),-2))
        val details = column(a)
        details.addView(label(a,food.name,18f,true))
        details.addView(label(a,"${food.minutes} min  •  ★ ${food.rating}",14f))
        details.addView(label(a,money(food.price),17f,true,orange))
        r.addView(details,LinearLayout.LayoutParams(0,-2,1f))
        r.addView(label(a,"›",27f,true))
        val p = LinearLayout.LayoutParams(-1,-2).apply { bottomMargin=dp(a,10) }
        parent.addView(r,p); r.setOnClickListener { onClick() }
    }
    fun money(value: Double) = String.format(java.util.Locale.US,"$%.2f",value)
    fun openDetail(a: Activity, id: Int) = a.startActivity(Intent(a,DetailActivity::class.java).putExtra("food_id",id))
    fun section(c: Context, title: String, parent: LinearLayout) {
        val t = label(c,title,21f,true); t.setPadding(0,dp(c,18),0,dp(c,12)); parent.addView(t)
    }
}
