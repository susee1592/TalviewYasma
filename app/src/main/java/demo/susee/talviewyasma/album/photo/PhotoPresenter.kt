package demo.susee.talviewyasma.album.photo

import android.view.View
import demo.susee.talviewyasma.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotoPresenter(val view: PhotoContract.View) : PhotoContract.Presenter {
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
            }, { error ->
                view.showError(error.message)
            })
    }
}
