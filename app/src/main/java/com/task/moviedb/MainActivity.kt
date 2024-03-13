package com.task.moviedb

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginEnd
import androidx.core.view.setPadding
import com.google.android.material.card.MaterialCardView
import com.task.moviedb.data.Movie


class MainActivity : AppCompatActivity() {

    private lateinit var moviesScrollView: HorizontalScrollView
    private lateinit var tvShowScrollView: HorizontalScrollView
    private lateinit var popularPeopleScrollView: HorizontalScrollView
    private lateinit var moviesContainer: LinearLayout
    private lateinit var tvShowContainer: LinearLayout
    private lateinit var popularPeopleContainer: LinearLayout

    val submenu = listOf("Top Rated", "Upcoming", "Now Playing", "Popular")
    val submenuImage = listOf(
        R.drawable.cinema_pop_corn_popcorn_movies_svgrepo_com,
        R.drawable.cinema_glasses,
        R.drawable.cinema,
        R.drawable.cinema_hd
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        moviesScrollView = findViewById(R.id.movies_scroll_view)
        moviesContainer = moviesScrollView.findViewById(R.id.movies_list)
        tvShowScrollView = findViewById(R.id.tv_show_scroll_view)
        tvShowContainer = tvShowScrollView.findViewById(R.id.tv_show_list)
        popularPeopleScrollView = findViewById(R.id.popular_people_scroll_view)
        popularPeopleContainer = popularPeopleScrollView.findViewById(R.id.popular_people_list)
//
        addSubmenuToContainer(submenu, moviesContainer)
        addSubmenuToContainer(submenu, tvShowContainer)
        addSubmenuToContainer(submenu, popularPeopleContainer)

    }

    private fun Int.dp(): Int {
        return resources.displayMetrics.density.toInt() * this
    }

    @SuppressLint("DiscouragedApi", "ResourceAsColor")
    fun addSubmenuToContainer(submenus: List<String>, container: LinearLayout) {
        for (i in submenus.indices) {
            if (container.id == R.id.popular_people_list && submenus[i] != "Popular") continue

            val parent = CardView(this)
            val layoutPar =  LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutPar.rightMargin = 32.dp()
            parent.layoutParams = layoutPar
            val cardView = LayoutInflater.from(this).inflate(R.layout.menu_card_view, parent) as CardView
            cardView.findViewById<ImageView>(R.id.submenu_image).setImageResource(submenuImage[i])
            cardView.findViewById<TextView>(R.id.submenu_text).text = submenu[i]
            cardView.setOnClickListener {
                val goToListIntent = Intent(this@MainActivity, ListActivity::class.java)
                startActivity(goToListIntent)
            }
            container.addView(cardView)
        }
    }
}