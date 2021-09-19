package dev.muthuram.nasapictures.data.model

import android.os.Bundle




data class NavigationModel(
    val clazz: Class<*>,
    val finishCurrent: Boolean = false,
    val extras: Bundle? = null
)