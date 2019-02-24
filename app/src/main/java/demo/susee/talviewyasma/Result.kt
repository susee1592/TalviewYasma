package demo.susee.talviewyasma

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class Result {
    @Parcelize
    data class Post(val id: Int, val userId: Int, val title: String, val body: String) : Parcelable

    data class Comment(val id: Int, val postId: Int, val name: String, val email: String, val body: String)

    data class Photo(val id: Int, val albumId: Int, val title: String, val url: String, val thumbnailUrl: String)

    data class Album(val id: Int, val userId: Int, val title: String)
}
