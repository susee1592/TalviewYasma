package demo.susee.talviewyasma.post

import android.os.AsyncTask
import demo.susee.talviewyasma.ApiService
import demo.susee.talviewyasma.Result
import demo.susee.talviewyasma.post.offline.PostRepository
import demo.susee.talviewyasma.user.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostPresenter(var view: PostContract.View, var repo: PostRepository?,var usrRepo: UserRepository?) :
    PostContract.Presenter {
    override fun postClicked(post: Result.Post) {
        view.postDetails(post)
    }

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
                object : AsyncTask<Void, Void, Void>() {
                    override fun doInBackground(vararg voids: Void): Void? {
                        if (repo?.size == 0) {
                            var ite = result?.iterator()
                            while (ite?.hasNext()!!) {
                                var cr = ite?.next()
                                repo?.insertPost(cr.id, cr.userId, cr.title, cr.body)
                            }
                        }
                        return null
                    }
                }.execute()
            }, { error ->
                view.showError(error.message)
            })
    }

    override fun getUsers() {
        var repository = ApiService.create()
        repository.getAllUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                object : AsyncTask<Void, Void, Void>() {
                    override fun doInBackground(vararg voids: Void): Void? {
                        if (usrRepo?.size == 0) {
                            var ite = result?.iterator()
                            while (ite?.hasNext()!!) {
                                var cr = ite?.next()
                                usrRepo?.insertUser(cr.id, cr.name,cr.username,cr.email,cr.phone,cr.website,null,null)
                            }
                        }
                        return null
                    }
                }.execute()
            }, { error ->
            })
    }
}
