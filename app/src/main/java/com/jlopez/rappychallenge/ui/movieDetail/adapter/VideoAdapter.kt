package com.jlopez.rappychallenge.ui.movieDetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jlopez.rappychallenge.databinding.VideoItemBinding
import com.jlopez.rappychallenge.source.local.entity.VideoEntity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
class VideoAdapter(
    private val clickListener: ClickListener,
    private val context: Context,
    private val lifecycle: Lifecycle
): ListAdapter<VideoEntity, VideoAdapter.VideoViewHolder>(VideoAdapter.DiffCallback){

    object DiffCallback : DiffUtil.ItemCallback<VideoEntity>() {
        override fun areItemsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
            return false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.VideoViewHolder {
        return VideoAdapter.VideoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: VideoAdapter.VideoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener, context, lifecycle)
    }

    class   VideoViewHolder  constructor(private val binding: VideoItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: VideoEntity,
            clickListener: ClickListener,
            context: Context,
            lifecycle: Lifecycle
        ){


            binding.video = item

            lifecycle.addObserver(binding.videoView)

            binding.videoView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = item.key
                    youTubePlayer.loadVideo(videoId, 0f)
                    youTubePlayer.pause()
                }
            })

        }


        companion object {
            fun from(parent: ViewGroup): VideoAdapter.VideoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VideoItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return VideoAdapter.VideoViewHolder(binding)
            }
        }
    }


    interface ClickListener {

    }




}