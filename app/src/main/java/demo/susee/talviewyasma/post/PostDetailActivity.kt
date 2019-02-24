package demo.susee.talviewyasma.post

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import demo.susee.talviewyasma.R
import demo.susee.talviewyasma.Result
import demo.susee.talviewyasma.post.comment.CommentContract
import demo.susee.talviewyasma.post.comment.CommentPresenter
import kotlinx.android.synthetic.main.activity_post_detail.*
import kotlinx.android.synthetic.main.item_comment.view.*

class PostDetailActivity : AppCompatActivity(), CommentContract.View {
    var adapter: CommentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        val post = intent.getParcelableExtra<Result.Post>("post")
        val presenter = CommentPresenter(this)
        adapter = CommentAdapter()
        commentRV.adapter = adapter
        presenter.getPost(post)
        presenter.getComments(post.id)
    }

    override fun showPost(post: Result.Post) {
        titleTV.text = post.title
        bodyTV.text = post.body
    }

    override fun showComments(result: ArrayList<Result.Comment>) {
        adapter?.setData(result)
    }

    override fun showError(str: String?) {
        Toast.makeText(baseContext, str, Toast.LENGTH_SHORT).show()
    }

    override fun showNoResults() {
        Toast.makeText(baseContext, "No Results Found!", Toast.LENGTH_SHORT).show()
    }

    class CommentAdapter : RecyclerView.Adapter<CommentAdapter.MyViewHolder>() {
        var res: ArrayList<Result.Comment> = ArrayList<Result.Comment>()

        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_comment, p0, false))
        }

        fun setData(result: ArrayList<Result.Comment>) {
            res.addAll(result)
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return res.size
        }

        override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
            var comment = res[p1]
            p0.itemView.name.text = comment.name
            p0.itemView.email.text = comment.email
            p0.itemView.body.text = comment.body
        }
    }
}
