package demo.susee.talviewyasma.post

import demo.susee.talviewyasma.Result

interface PostContract{
    interface View{
        fun showPosts(res:ArrayList<Result.Post>)

        fun showNoResult()

        fun showError(str:String?)
    }

    interface Presenter{
        fun getPosts()
    }
}
