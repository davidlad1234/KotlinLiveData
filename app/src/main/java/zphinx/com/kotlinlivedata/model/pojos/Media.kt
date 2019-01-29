package zphinx.com.kotlinlivedata.model.pojos

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class Media() : Parcelable {

    @SerializedName("m")
    var m: String? = null

    constructor(parcel: Parcel) : this() {
        m = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(m)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Media(m=$m)"
    }

    companion object CREATOR : Parcelable.Creator<Media> {
        override fun createFromParcel(parcel: Parcel): Media {
            return Media(parcel)
        }

        override fun newArray(size: Int): Array<Media?> {
            return arrayOfNulls(size)
        }
    }


}