package com.test.assessment.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elyeproj.loaderviewlibrary.LoaderImageView
import com.elyeproj.loaderviewlibrary.LoaderTextView
import com.orhanobut.hawk.Hawk
import com.test.assessment.R
import com.test.assessment.constract.BOOKMARK_LIST
import com.test.assessment.model.AlbumResponse
import com.test.assessment.model.Result
import com.test.assessment.service.HomePageService
import com.test.assessment.view.BookmarkFragment
import kotlinx.android.synthetic.main.album_list.view.*

class AlbumAdapter(
    val context: Context,
    private val albums: List<Result>?,
    val isBookmarkFragment: Boolean = false
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {


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


    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var lIvAlbum: LoaderImageView = itemView.lIvAlbum
        var ltvTitle: LoaderTextView = itemView.ltvTitle
        var ltvSubtitle: LoaderTextView = itemView.ltvSubtitle
        var ibBookmark: ImageButton = itemView.ibBookmark
        var ltvSubtitle1: LoaderTextView = itemView.ltvSubtitle1
        var ltvSubtitle2: LoaderTextView = itemView.ltvSubtitle2

        fun bind(album: Result) {

            if (isBookmarkFragment) {
                ibBookmark.visibility = View.GONE
            } else {
                ibBookmark.visibility = View.VISIBLE
            }

            album.let {
                if (it.isBookmark) {
                    ibBookmark.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_bookmark_white_18dp
                        )
                    )
                } else {
                    ibBookmark.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_bookmark_border_white_18dp
                        )
                    )
                }

                Glide.with(context).load(album.artworkUrl100).into(lIvAlbum)
                ltvTitle.text = album.artistName
                ltvSubtitle.text = album.collectionName
                ltvSubtitle1.text = album.copyright
                ltvSubtitle2.text = album.currency +" : " + album.collectionPrice
                ibBookmark.setOnClickListener {

                    ibBookmark.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_bookmark_white_18dp
                        )
                    )

                    if (!album.isBookmark) {
                        if (!HomePageService.saveBookMarkList(album)) {
                            var alert = AlertDialog.Builder(context)
                            alert.setMessage("Please Try again later")
                            alert.setPositiveButton("Close", null)
                            alert.show()
                        } else {
                            album.isBookmark = true
                            Toast.makeText(context, "bookmark success", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        var alert = AlertDialog.Builder(context)
                        alert.setMessage("Already bookmark")
                        alert.setPositiveButton("Close", null)
                        alert.show()
                    }
                }
            }

        }
    }
}