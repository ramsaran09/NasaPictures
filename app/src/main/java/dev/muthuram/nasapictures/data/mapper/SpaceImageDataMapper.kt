package dev.muthuram.nasapictures.data.mapper

import dev.muthuram.nasapictures.data.model.ERROR_NO_DATA
import dev.muthuram.nasapictures.data.model.SpaceNewsModel
import dev.muthuram.nasapictures.handler.CustomResponse
import dev.muthuram.nasapictures.handler.LocalException
import dev.muthuram.nasapictures.helper.toArrayList

class SpaceImageDataMapper {
    companion object {
        fun map(spaceImageList : List<SpaceNewsModel>) : CustomResponse<ArrayList<SpaceNewsModel>,LocalException> {
            return if (spaceImageList.isNotEmpty()) {
                CustomResponse.Success(spaceImageList.toArrayList())
            } else CustomResponse.Failure(LocalException(ERROR_NO_DATA))
        }
    }
}