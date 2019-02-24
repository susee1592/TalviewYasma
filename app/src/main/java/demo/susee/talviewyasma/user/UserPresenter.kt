package demo.susee.talviewyasma.user

import demo.susee.talviewyasma.ApiService
import demo.susee.talviewyasma.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserPresenter {
    fun getUser(id: Int): Result.User? {
        var repository = ApiService.create()
        return repository.getUser(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .blockingFirst()
    }
}

