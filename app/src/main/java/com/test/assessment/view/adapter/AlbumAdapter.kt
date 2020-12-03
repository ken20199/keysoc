package com.test.assessment.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elyeproj.loaderviewlibrary.LoaderImageView
import com.elyeproj.loaderviewlibrary.LoaderTextView
import com.test.assessment.R
import com.test.assessment.model.AlbumResponse
import com.test.assessment.model.Result
import kotlinx.android.synthetic.main.album_list.view.*

class AlbumAdapter(val context: Context, private val albums: List<Result>?) :RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.album_list, parent, false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int {
        var size = 0
        albums.let {
            size = it?.size?.toInt() ?: 0
        }
        return size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        albums?.get(position)?.let { holder.bind(it) }
    }


    inner class AlbumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var lIvAlbum: LoaderImageView = itemView.lIvAlbum
        var ltvTitle: LoaderTextView = itemView.ltvTitle
        var ltvSubtitle: LoaderTextView = itemView.ltvSubtitle
        var ibBookmark: ImageButton = itemView.ibBookmark

        fun bind(album: Result) {

            album.let {
                Glide.with(context).load(album.artworkUrl100).into(lIvAlbum)
                ltvTitle.text = album.artistName
                ltvSubtitle.text = album.collectionName
                ibBookmark.setOnClickListener {
                    Toast.makeText(context,"bookmark success",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}