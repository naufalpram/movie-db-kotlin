package com.task.moviedb

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.task.moviedb.data.Movie

class DetailActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movie = intent.getParcelableExtra(SELECTED_MOVIE, Movie::class.java)
        if (movie != null) {
            Log.w("IntentData", movie.title)
            insertDataToUI(movie)
        }
    }

    private fun insertDataToUI(movie: Movie) {
        val backgroundMoviePoster = findViewById<ImageView>(R.id.movie_image)
        backgroundMoviePoster.setImageResource(movie.posterResId)
        val movieTitle = findViewById<TextView>(R.id.movie_title)
        movieTitle.text = movie.title
        val movieDesc = findViewById<TextView>(R.id.movie_description)
        movieDesc.text = movie.description
        val movieRating = findViewById<TextView>(R.id.movie_rating)
        movieRating.text = "Rating: ${movie.rating}/5"
    }

    companion object {
        const val SELECTED_MOVIE = "selected_movie"
    }
}