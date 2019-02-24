package demo.susee.talviewyasma.post

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import demo.susee.talviewyasma.R
import demo.susee.talviewyasma.Result
import demo.susee.talviewyasma.post.offline.PostRepository
import demo.susee.talviewyasma.user.UserRepository
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.item_post.view.*

class PostFragment : Fragment(), PostContract.View {
    var adapter: PostAdapter? = null
    var repo: PostRepository? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        repo = PostRepository(context)
        var userRepo = UserRepository(context)
        var presenter = PostPresenter(this, repo,userRepo)
        adapter = PostAdapter(presenter, context)
        postList.adapter = adapter

        swipeRefresh.post {
            swipeRefresh.isRefreshing = true
            presenter.getPosts()
            presenter.getUsers()

        }
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            presenter.getPosts()
        }
        super.onActivityCreated(savedInstanceState)
    }

    override fun showPosts(res: ArrayList<Result.Post>) {
        swipeRefresh.isRefreshing = false
        adapter?.setData(res)
    }

    override fun postDetails(post: Result.Post) {
        var intent = Intent(context, PostDetailActivity::class.java)
        intent.putExtra("post", post)
        startActivity(intent)
    }

    override fun showNoResult() {
        swipeRefresh.isRefreshing = false
        Toast.makeText(context, "No Results Found!", Toast.LENGTH_SHORT).show()
    }

    override fun showError(str: String?) {
        swipeRefresh.isRefreshing = false
        Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show()
        repo?.allPosts?.observe(this, Observer {
            var ite = it?.iterator()
            var res = ArrayList<Result.Post>()
            while (ite?.hasNext()!!) {
                var cr = ite?.next()
                res.add(Result.Post(cr.id, cr.userId, cr.title, cr.body))
            }
            adapter?.setData(res)
        })
    }

    class PostAdapter(
        var presenter: PostPresenter,
        val context: Context?
    ) : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {
        var res: ArrayList<Result.Post> = ArrayList<Result.Post>()

        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_post, p0, false))
        }

        fun setData(result: ArrayList<Result.Post>) {
            res.addAll(result)
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return res.size
        }

        override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
            var post = res[p1]
            p0.itemView.title.text = post.title
            p0.itemView.body.text = post.body
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    val user = UserRepository(context).getUser(post.userId)
                    if (user != null) {
                        p0.itemView.name.text = user.name
                        p0.itemView.email.text = user.email
                    }
                    return null
                }
            }.execute()
            p0.itemView.setOnClickListener { presenter.postClicked(post) }
        }
    }
}
