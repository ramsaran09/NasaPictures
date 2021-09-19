package dev.muthuram.nasapictures.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpaceNewsModel(
    val copyright : String?,
    val date : String?,
    val explanation : String?,
    val hdurl : String?,
    val media_type : String?,
    val service_version : String?,
    val title : String?,
    val url : String?,
) : Parcelable