package dev.muthuram.nasapictures.repository

import dev.muthuram.nasapictures.data.mapper.SpaceImageDataMapper
import dev.muthuram.nasapictures.data.model.SpaceNewsModel
import dev.muthuram.nasapictures.handler.CustomResponse
import dev.muthuram.nasapictures.handler.LocalException
import dev.muthuram.nasapictures.manager.AssetsManager

class SpaceInfoRepository(
    private val assetsManager : AssetsManager
){

    fun getSpaceImageData() : CustomResponse<ArrayList<SpaceNewsModel>,LocalException> {
        return SpaceImageDataMapper.map(assetsManager.readSpaceDataFromAssetFile())
    }
}