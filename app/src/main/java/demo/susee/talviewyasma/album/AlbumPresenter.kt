package demo.susee.talviewyasma.album

import demo.susee.talviewyasma.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlbumPresenter(var view: AlbumContract.View) :
    AlbumContract.Presenter {
    override fun getAlbums() {
        var repository = ApiService.create()
        repository.getAlbums().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result.size == 0)
                    view.showNoResult()
                else
                    view.showAlbums(result)
            }, { error ->
                view.showError(error.message)
            })
    }
}
