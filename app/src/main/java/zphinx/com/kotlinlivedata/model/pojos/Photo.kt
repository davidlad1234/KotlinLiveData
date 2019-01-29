package zphinx.com.kotlinlivedata.model.pojos

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.util.Date

class Photo() : Parcelable {

    @SerializedName("title")
    var title: String? = null
    @SerializedName("link")
    var link: String? = null
    @SerializedName("media")
    var media: Media? = null

    @SerializedName("date_taken")
    var dateTaken: Date? = null
    @SerializedName("description")
    var description: String? = null

    @SerializedName("published")
    var published: Date? = null
    @SerializedName("author")
    var author: String? = null
    @SerializedName("author_id")
    var authorId: String? = null
    @SerializedName("tags")
    var tags: String? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        link = parcel.readString()
        media = parcel.readParcelable(Media::class.java.classLoader)
        description = parcel.readString()
        author = parcel.readString()
        authorId = parcel.readString()
        tags = parcel.readString()
        dateTaken = Date(parcel.readLong())
        published = Date(parcel.readLong())
    }

    override fun toString(): String {
        return "Photo{" +
                "title='" + title + '\''.toString() +
                ", link='" + link + '\''.toString() +
                ", media=" + media +
                ", dateTaken=" + dateTaken +
                ", description='" + description + '\''.toString() +
                ", published=" + published +
                ", author='" + author + '\''.toString() +
                ", authorId='" + authorId + '\''.toString() +
                ", tags='" + tags + '\''.toString() +
                '}'.toString()
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(link)
        parcel.writeParcelable(media, flags)
        parcel.writeString(description)
        parcel.writeString(author)
        parcel.writeString(authorId)
        parcel.writeString(tags)
        parcel.writeLong(dateTaken?.time?:0)
        parcel.writeLong(published?.time?:0)
       }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }

}