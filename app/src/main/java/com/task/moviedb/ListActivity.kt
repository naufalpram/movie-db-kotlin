package com.task.moviedb

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.task.moviedb.adapter.MovieListAdapter
import com.task.moviedb.data.Movie

class ListActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private val list = ArrayList<Movie>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        fun getArray(resId: Int) = resources.getStringArray(resId)
        fun movieFactory(arrId: Int, drawId: Int): Movie {
            return Movie(arrId, getArray(arrId)[0], getArray(arrId)[1], drawId, (1..5).random())
        }
        val movies = mutableListOf(
            movieFactory(R.array.argylle, R.drawable.argylle),
            movieFactory(R.array.batman_v_superman, R.drawable.batman_v_superman),
            movieFactory(R.array.interstellar, R.drawable.interstellar),
            movieFactory(R.array.migration, R.drawable.migration),
            movieFactory(R.array.star_wars, R.drawable.star_wars),
            movieFactory(R.array.the_beekeeper, R.drawable.the_beekeeper),
            movieFactory(R.array.the_dark_knight, R.drawable.the_dark_knight),
            movieFactory(R.array.wonder_woman, R.drawable.wonder_woman),
            movieFactory(R.array.wonka, R.drawable.wonka)
        )

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var layoutManager = if (applicationContext.resources.configuration.orientation
            == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }
        val listMoviesAdapter = MovieListAdapter(movies)

        rv = findViewById(R.id.recycler_view)
        rv.layoutManager = layoutManager
        rv.adapter = listMoviesAdapter

        list.addAll(movies)

        listMoviesAdapter.setOnItemClickCallback(object : MovieListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Movie) {
                val intent = Intent(this@ListActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.SELECTED_MOVIE, data)
                startActivity(intent)
            }
        })
    }

    private fun Int.dp(): Int {
        return resources.displayMetrics.density.toInt() * this
    }

    private fun addMoviesToContainer(movieList: List<Movie>, container: LinearLayout) {
        for (movie in movieList) {
            val cardView = CardView(this)
            val width = 160.dp()
            val height = 240.dp()
            val marginHorizontal = 6.dp()
            val cardLayoutParams = LinearLayout.LayoutParams(width, height)
            cardLayoutParams.leftMargin = marginHorizontal
            cardLayoutParams.rightMargin = marginHorizontal
            cardView.layoutParams = cardLayoutParams
            cardView.radius = 8.dp().toFloat()

            val imageView = ImageView(this)
            imageView.id = movie.id
            val imgLayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            imageView.layoutParams = imgLayoutParams
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP

            imageView.setImageResource(movie.posterResId)
            cardView.addView(imageView)

            cardView.setOnClickListener {
                val goToDetailIntent = Intent(this@ListActivity, DetailActivity::class.java)
                goToDetailIntent.putExtra("movie", movie)
                startActivity(goToDetailIntent)
            }
            container.addView(cardView)
        }
    }
}