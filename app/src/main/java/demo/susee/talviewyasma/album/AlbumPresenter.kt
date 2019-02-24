package demo.susee.talviewyasma.album

import android.os.AsyncTask
import demo.susee.talviewyasma.ApiService
import demo.susee.talviewyasma.album.offline.AlbumRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlbumPresenter(var view: AlbumContract.View, var repo: AlbumRepository?) :
    AlbumContract.Presenter {
    override fun albumClicked(id: Int) {
        view.albumPhotos(id)
    }

    override fun getAlbums() {
        var repository = ApiService.create()
        repository.getAlbums().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result.size == 0)
                    view.showNoResult()
                else
                    view.showAlbums(result)
                object : AsyncTask<Void, Void, Void>() {
                    override fun doInBackground(vararg voids: Void): Void? {
                        if (repo?.size == 0) {
                            var ite = result?.iterator()
                            while (ite?.hasNext()!!) {
                                var cr = ite?.next()
                                repo?.insertAlbum(cr.id, cr.userId, cr.title)
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
