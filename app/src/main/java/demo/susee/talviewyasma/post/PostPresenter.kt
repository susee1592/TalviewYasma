package demo.susee.talviewyasma.post

import demo.susee.talviewyasma.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostPresenter(var view: PostContract.View) :
    PostContract.Presenter {
    override fun getPosts() {
        var repository = ApiService.create()
        repository.getposts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result.size == 0)
                    view.showNoResult()
                else
                    view.showPosts(result)
            }, { error ->
                view.showError(error.message)
            })
    }
}
