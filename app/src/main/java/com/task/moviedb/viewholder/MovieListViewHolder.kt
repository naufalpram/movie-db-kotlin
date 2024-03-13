package com.task.moviedb.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.moviedb.R

class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title: TextView = itemView.findViewById(R.id.item_title)
    var description: TextView = itemView.findViewById(R.id.item_description)
    var rating: TextView = itemView.findViewById(R.id.item_rating)
    var image: ImageView = itemView.findViewById(R.id.item_image)
}
