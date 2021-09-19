package dev.muthuram.nasapictures.helper

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.getSystemService
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


@SuppressLint("MissingPermission")
@Suppress("DEPRECATION")
fun Context.isInternetAvailable(): Boolean? {
    val cm = getSystemService<ConnectivityManager>()
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm?.run {
            getNetworkCapabilities(this.activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
    } else {
        return cm?.run {
            activeNetworkInfo?.run {
                this.isConnected
            }
        }
    }
}


inline fun <T> T?.ifNotNull(block: (T) -> Unit): Boolean {
    return if (this !== null) {
        block(this)
        true
    } else false
}

inline fun <T> T?.ifNull(block: () -> Unit): Boolean {
    return if (this === null) {
        block()
        true
    } else false
}
fun String?.defaultValue(defaultValue: String = "") = this ?: defaultValue


fun <T> List<T>.toArrayList(): ArrayList<T>{
    return ArrayList(this)
}


fun drawableFromUrl(url: String?): Drawable {
    val x: Bitmap
    val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
    connection.connect()
    val input: InputStream = connection.inputStream
    x = BitmapFactory.decodeStream(input)
    return BitmapDrawable(Resources.getSystem(), x)
}