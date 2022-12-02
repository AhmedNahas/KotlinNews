package tri.pro.kotlinnews.models

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Data(
    val children: ArrayList<ObjectResponse>?,
    val selftext: String?,
    val thumbnail: String?,
    val title: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(ObjectResponse.CREATOR),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(children)
        parcel.writeString(selftext)
        parcel.writeString(thumbnail)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}