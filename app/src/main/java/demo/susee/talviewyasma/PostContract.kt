package demo.susee.talviewyasma

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
