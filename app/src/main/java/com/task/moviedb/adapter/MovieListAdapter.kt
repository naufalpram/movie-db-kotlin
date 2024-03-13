package com.task.moviedb.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.moviedb.DetailActivity
import com.task.moviedb.R
import com.task.moviedb.data.Movie
import com.task.moviedb.viewholder.MovieListViewHolder

class MovieListAdapter(private val movieList: List<Movie>): RecyclerView.Adapter<MovieListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MovieListViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.title.text = movieList[position].title
        holder.description.text = movieList[position].description
        holder.rating.text = "Rating: ${movieList[position].rating}/5"
        holder.image.setImageResource(movieList[position].posterResId)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(movieList[holder.adapterPosition])
        }
    }
    override fun getItemCount(): Int = movieList.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}