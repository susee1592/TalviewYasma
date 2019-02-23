package demo.susee.talviewyasma

class Result {
    data class Post(val id: Int, val userId: Int, val title: String, val body: String)

    data class Comment(val id: Int, val postId: Int, val name: String, val email: String, val body: String)

    data class Photo(val id: Int, val albumId: Int, val title: String, val url: String, val thumbnailUrl: String)

    data class Album(val id: Int, val userId: Int, val title: String)
}
