package dev.muthuram.nasapictures.ui.image_details

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.muthuram.nasapictures.data.model.SpaceNewsModel
import dev.muthuram.nasapictures.helper.coMap
import dev.muthuram.nasapictures.helper.defaultValue
import dev.muthuram.nasapictures.helper.drawableFromUrl


class SpaceImageDetailsViewModel : ViewModel() {

    private val spaceImageDataLd = MutableLiveData<SpaceNewsModel>()

    val spaceImageData: LiveData<SpaceNewsModel> = spaceImageDataLd

    val imageUrl: LiveData<List<Drawable>> = coMap(spaceImageData) {
        arrayListOf(
            drawableFromUrl(it.hdurl.defaultValue()),
            drawableFromUrl(it.url.defaultValue())
        )
    }

    fun processIntent(intent: Intent?) {
        if (intent != null) {
            spaceImageDataLd.value = intent.getParcelableExtra(KEY_SPACE_IMAGE_DATA)
        }
    }

    companion object {
        const val KEY_SPACE_IMAGE_DATA = "key.space.image.data"
    }
}