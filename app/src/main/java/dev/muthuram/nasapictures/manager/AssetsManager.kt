package dev.muthuram.nasapictures.manager

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.muthuram.nasapictures.data.model.SpaceNewsModel

class AssetsManager(
    private val context: Context
) {

    fun readSpaceDataFromAssetFile(): List<SpaceNewsModel> =
        Gson().fromJson(
            context.assets.open("data.json").reader(),
            object : TypeToken<List<SpaceNewsModel>>() {}.type
        )
}