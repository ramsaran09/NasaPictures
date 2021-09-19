package dev.muthuram.nasapictures.ui.home

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.muthuram.nasapictures.data.model.SpaceNewsModel
import dev.muthuram.nasapictures.data.model.NavigationModel
import dev.muthuram.nasapictures.handler.CustomResponse
import dev.muthuram.nasapictures.repository.SpaceInfoRepository
import dev.muthuram.nasapictures.ui.image_details.SpaceImageDetailsActivity
import dev.muthuram.nasapictures.ui.image_details.SpaceImageDetailsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val spaceInfoRepository: SpaceInfoRepository
) : ViewModel() {

    private val navigateLd = MutableLiveData<NavigationModel>()
    private val errorLd = MutableLiveData<String>()
    private val loaderLd = MutableLiveData<Boolean>()
    private val spaceImageDataLd = MutableLiveData<ArrayList<SpaceNewsModel>>()

    init {
        getSpaceImageData()
    }

    val navigate: LiveData<NavigationModel> = navigateLd
    val error: LiveData<String> = errorLd
    val loader: LiveData<Boolean> = loaderLd
    val spaceImageData: LiveData<ArrayList<SpaceNewsModel>> = spaceImageDataLd

    private fun getSpaceImageData() {
        loaderLd.value = true
        viewModelScope.launch {
            when (val response = spaceInfoRepository.getSpaceImageData()) {
                is CustomResponse.Success -> spaceImageDataLd.value = response.data!!
                is CustomResponse.Failure -> errorLd.value = response.error.message
                else -> Unit
            }.also {
                delay(1000)
                loaderLd.value = false
            }
        }
    }

    fun moreDetails(spaceNewsModel: SpaceNewsModel) {
        val bundle = Bundle().also {
            it.putParcelable(SpaceImageDetailsViewModel.KEY_SPACE_IMAGE_DATA, spaceNewsModel)
        }
        navigateLd.value = NavigationModel(
            SpaceImageDetailsActivity::class.java,
            extras = bundle
        )
    }
}