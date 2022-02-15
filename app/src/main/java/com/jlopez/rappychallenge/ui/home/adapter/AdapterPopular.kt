package com.jlopez.rappychallenge.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jlopez.rappychallenge.databinding.MovieItemBinding
import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity

class AdapterPopular constructor(
    private val clickListener: MoviePopularClickListener,
    private val context: Context
) : ListAdapter<MoviePopularEntity, AdapterPopular.PopularViewHolder>(DiffCallback) {


    object DiffCallback : DiffUtil.ItemCallback<MoviePopularEntity>() {
        override fun areItemsTheSame(oldItem: MoviePopularEntity, newItem: MoviePopularEntity): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: MoviePopularEntity, newItem: MoviePopularEntity): Boolean {
            return false
        }
    }


    class PopularViewHolder constructor(private val binding: MovieItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: MoviePopularEntity,
            clickListener: MoviePopularClickListener,
            context: Context
        ){

            val url = "https://www.themoviedb.org/t/p/w220_and_h330_face/${item.posterPath}"
            Glide.with(context)
                .load(url)
                .into(binding.imageViewPoster)

            binding.textViewRanking.text = item.voteAverage.toString()
            binding.textViewName.text = item.title
            binding.textViewDate.text = item.releaseDate

            binding.itemContent.setOnClickListener {
                clickListener.onClick(item)
            }

        }


        companion object {
            fun from(parent: ViewGroup): PopularViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return PopularViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener, context)
    }


    interface MoviePopularClickListener {
        fun onClick(item: MoviePopularEntity)
    }

}