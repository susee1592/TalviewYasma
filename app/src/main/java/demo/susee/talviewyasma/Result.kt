package demo.susee.talviewyasma

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class Result {
    @Parcelize
    data class Post(val id: Int, val userId: Int, val title: String, val body: String) : Parcelable

    data class Comment(val id: Int, val postId: Int, val name: String, val email: String, val body: String)

    data class Photo(val id: Int, val albumId: Int, val title: String, val url: String, val thumbnailUrl: String)

    data class Album(val id: Int, val userId: Int, val title: String)

    data class Company(val name: String, val catchPhrase: String, val bs: String)

    data class Geo(val lat: String, val lng: String)

    data class Address(val street: String, val suite: String, val city: String, val zipcode: String,val  geo: Geo)

    data class User(val id: Int, val name: String, val username: String, val email: String
                     , val phone: String, val website: String, val company: Company,val address: Address)




}
