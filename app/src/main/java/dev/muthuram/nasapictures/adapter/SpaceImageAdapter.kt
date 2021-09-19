package dev.muthuram.nasapictures.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dev.muthuram.nasapictures.R
import dev.muthuram.nasapictures.data.model.SpaceNewsModel
import kotlinx.android.synthetic.main.model_grid_images.view.*

class SpaceImageAdapter (
    private val context : Context,
    val onImageClickListener : (SpaceNewsModel) -> Unit,
) : RecyclerView.Adapter<SpaceImageAdapter.SpaceImageViewHolder>(){

    private val imagesData : ArrayList<SpaceNewsModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SpaceImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.model_grid_images, parent, false)
        )

    override fun onBindViewHolder(holder: SpaceImageViewHolder, position: Int) {
        val item = imagesData[position]
        holder.tvTitle.isSelected = true
        holder.tvTitle.text = item.title

        Glide.with(context).load(item.url).apply(
            RequestOptions()
            .override(150, 150)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
            .placeholder(R.drawable.ic_image_placeholder)
            .into(holder.ivSpaceImage)
    }

    override fun getItemCount(): Int = imagesData.size

    fun updateList(spaceImageData: ArrayList<SpaceNewsModel>){
        imagesData.clear()
        imagesData.addAll(spaceImageData)
        notifyDataSetChanged()
    }

    inner class SpaceImageViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val cvArticleContainer : CardView = view.uiCvArticleContainer
        val ivSpaceImage : AppCompatImageView = view.uiIvSpaceImage
        val tvTitle : AppCompatTextView = view.uiTvTitle

        init {
            cvArticleContainer.setOnClickListener { onImageClickListener.invoke(imagesData[adapterPosition]) }
        }
    }
}