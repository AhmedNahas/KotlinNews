package tri.pro.kotlinnews.models

import android.os.Parcel
import android.os.Parcelable

data class ObjectResponse(
    val data: Data?,
    val kind: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Data::class.java.classLoader),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(data, flags)
        parcel.writeString(kind)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ObjectResponse> {
        override fun createFromParcel(parcel: Parcel): ObjectResponse {
            return ObjectResponse(parcel)
        }

        override fun newArray(size: Int): Array<ObjectResponse?> {
            return arrayOfNulls(size)
        }
    }
}