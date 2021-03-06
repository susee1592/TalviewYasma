package demo.susee.talviewyasma

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    fun getposts(): Observable<ArrayList<Result.Post>>

    @GET("posts/{id}")
    fun getPostDetails(@Path("id") id: Int): Observable<Result.Post>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") id: Int): Observable<ArrayList<Result.Comment>>

    @GET("albums")
    fun getAlbums(): Observable<ArrayList<Result.Album>>

    @GET("albums/{id}")
    fun getAlbum(@Path("id") id: Int): Observable<ArrayList<Result.Album>>

    @GET("albums/{id}/photos")
    fun getPhotos(@Path("id") id: Int): Observable<ArrayList<Result.Photo>>

    @GET("users")
    fun getAllUser(): Observable<ArrayList<Result.User>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Observable<Result.User>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
