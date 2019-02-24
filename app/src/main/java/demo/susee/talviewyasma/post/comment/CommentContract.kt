package demo.susee.talviewyasma.post.comment

import demo.susee.talviewyasma.Result

interface CommentContract {
    interface View {
        fun showComments(result: ArrayList<Result.Comment>)

        fun showError(str: String?)

        fun showNoResults()

        fun showPost(post: Result.Post)

    }

    interface Presenter {
        fun getComments()

        fun getPost(post: Result.Post)
    }
}
