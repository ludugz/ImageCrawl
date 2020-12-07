package com.example.imagecrawler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagecrawler.R
import com.example.imagecrawler.convertUrl
import com.example.imagecrawler.loadImage
import com.example.imagecrawler.model.FlickerImage
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class ImageAdapter() : RecyclerView.Adapter<ImageViewHolder>() {
    private var data : FlickerImage? = null
    fun setData(image : FlickerImage) {
        data = image
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val photo = data?.photos?.photo?.get(position)
        photo?.let {
            loadImage(holder.itemView,convertUrl(photo.server, photo.id, photo.secret),holder.itemView.image_view_item)
        }
    }

    override fun getItemCount(): Int {
        return data?.photos?.photo?.size ?: 0
    }
}
