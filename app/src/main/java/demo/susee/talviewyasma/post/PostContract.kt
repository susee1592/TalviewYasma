package demo.susee.talviewyasma.post

import demo.susee.talviewyasma.Result

interface PostContract{
    interface View{
        fun showPosts(res:ArrayList<Result.Post>)

        fun showNoResult()

        fun showError(str:String?)

        fun postDetails(post: Result.Post)
    }

    interface Presenter{
        fun getPosts()

        fun getUsers()

        fun postClicked(post: Result.Post)
    }
}
