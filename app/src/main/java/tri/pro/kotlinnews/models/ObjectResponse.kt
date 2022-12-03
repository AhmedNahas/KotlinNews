package tri.pro.kotlinnews.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ObjectResponse(
    val data: Data?,
    val kind: String?
) : Parcelable