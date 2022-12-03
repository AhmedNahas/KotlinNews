package tri.pro.kotlinnews.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val children: ArrayList<ObjectResponse>?,
    val selftext: String?,
    val thumbnail: String?,
    val title: String?
) : Parcelable