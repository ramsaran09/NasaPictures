package dev.muthuram.nasapictures.helper

import android.app.Activity
import android.content.Intent
import dev.muthuram.nasapictures.data.model.NavigationModel


fun Activity.startActivity(navigationModel: NavigationModel) {
    val intent = Intent(this, navigationModel.clazz)
    if (navigationModel.extras != null)
        intent.putExtras(navigationModel.extras)
    startActivity(intent)
    if (navigationModel.finishCurrent)
        finish()
}


