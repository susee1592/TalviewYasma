package demo.susee.talviewyasma.album.photo

import android.os.AsyncTask
import android.view.View
import demo.susee.talviewyasma.ApiService
import demo.susee.talviewyasma.album.photo.offline.PhotoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotoPresenter(
    val view: PhotoContract.View,
    val repo: PhotoRepository?
) : PhotoContract.Presenter {
    override fun photoClicked(url: String, itemView: View) {
        view.photoDetails(url, itemView)
    }

    override fun getPhotos(id: Int) {
        var repository = ApiService.create()
        repository.getPhotos(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result.size == 0)
                    view.showNoResult()
                else
                    view.showPhotos(result)
                object : AsyncTask<Void, Void, Void>() {
                    override fun doInBackground(vararg voids: Void): Void? {
                        if (repo?.size == 0) {
                            var ite = result?.iterator()
                            while (ite?.hasNext()!!) {
                                var cr = ite?.next()
                                repo?.insertPhoto(cr.id, cr.albumId, cr.title, cr.url, cr.thumbnailUrl)
                            }
                        }
                        return null
                    }
                }.execute()

            }, { error ->
                view.showError(error.message)
            })
    }
}
