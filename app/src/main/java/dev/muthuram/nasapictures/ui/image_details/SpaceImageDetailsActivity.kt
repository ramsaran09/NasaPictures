package dev.muthuram.nasapictures.ui.image_details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.synnapps.carouselview.ImageListener
import dev.muthuram.nasapictures.R
import dev.muthuram.nasapictures.data.model.SpaceNewsModel
import dev.muthuram.nasapictures.helper.defaultValue
import dev.muthuram.nasapictures.helper.observeLiveData
import kotlinx.android.synthetic.main.activity_image_details.*
import org.koin.android.ext.android.inject


class SpaceImageDetailsActivity : AppCompatActivity() {

    private val spaceImageDetailsViewModel : SpaceImageDetailsViewModel by inject()
    private val imageListUri : ArrayList<Drawable> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        spaceImageDetailsViewModel.processIntent(intent)
        setUpUi()
        setUpListener()
    }

    private fun setUpUi() {
        spaceImageDetailsViewModel.spaceImageData.observeLiveData(this,::displaySpaceInfo)
        spaceImageDetailsViewModel.imageUrl.observeLiveData(this,::displayImage)
    }

    private fun setUpListener() {
        uiIvBack.setOnClickListener { onBackPressed() }
    }

    private fun displaySpaceInfo(spaceNewsModel: SpaceNewsModel) {
        uiTvNewsTitle.text = spaceNewsModel.title
        uiTvAuthorName.text = "${spaceNewsModel.copyright.defaultValue()} - ${spaceNewsModel.date.defaultValue()} "
        uiTvDescription.text = spaceNewsModel.explanation
    }

    private fun displayImage(imageList: List<Drawable>) {
        Log.d("imageList",imageList.toString())
        uiAnimationLoader.visibility = View.GONE
        imageListUri.addAll(imageList)
        carouselView.apply {
            setImageListener(imageListener)
            pageCount = imageList.size
        }
    }

    private var imageListener = ImageListener{ position, imageView ->
        imageView.setImageDrawable(imageListUri[position])
    }

}