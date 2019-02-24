package demo.susee.talviewyasma.post.comment

import demo.susee.talviewyasma.ApiService
import demo.susee.talviewyasma.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommentPresenter(var view: CommentContract.View) : CommentContract.Presenter {
    override fun getComments() {
        var repository = ApiService.create()
        repository.getComments()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result.size == 0)
                    view.showNoResults()
                else
                    view.showComments(result)
            }, { error ->
                view.showError(error.message)
            })
    }

    override fun getPost(post: Result.Post) {
        view.showPost(post)
    }

}
