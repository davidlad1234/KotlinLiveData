package zphinx.com.kotlinlivedata.model.pojos

/* Copyright 2018 freecodeformat.com */

import com.google.gson.annotations.SerializedName

import java.util.Date

/* Time: 2018-09-28 18:32:4 @author freecodeformat.com @website http://www.freecodeformat.com/json2javabean.php */
class Photos {

    @SerializedName("title")
    var title: String? = null
    @SerializedName("link")
    var link: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("modified")
    var modified: Date? = null
    @SerializedName("generator")
    var generator: String? = null
    @SerializedName("items")
    var photos: Array<Photo>? = null

    override fun toString(): String {
        return "Photos{" +
                "title='" + title + '\''.toString() +
                ", link='" + link + '\''.toString() +
                ", description='" + description + '\''.toString() +
                ", modified=" + modified +
                ", generator='" + generator + '\''.toString() +
                ", photos=" + photos +
                '}'.toString()
    }

}