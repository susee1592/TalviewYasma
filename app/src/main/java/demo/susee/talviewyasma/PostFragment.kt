package demo.susee.talviewyasma

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.item_post.view.*

class PostFragment : Fragment(), PostContract.View {
    var adapter: PostAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        adapter = PostAdapter(context)
        postList.adapter = adapter
        var presenter = PostPresenter(this)
        presenter.getPosts()
        super.onActivityCreated(savedInstanceState)
    }

    override fun showPosts(res: ArrayList<Result.Post>) {
        adapter?.setData(res)
    }

    override fun showNoResult() {
        Toast.makeText(context, "No Results Found!", Toast.LENGTH_SHORT).show()
    }

    override fun showError(str: String?) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }

    class PostAdapter(var context: Context?) : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {
        var res: ArrayList<Result.Post> = ArrayList<Result.Post>()

        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostAdapter.MyViewHolder {
            return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_post, p0, false))
        }

        fun setData(result: ArrayList<Result.Post>) {
            res.addAll(result)
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return res.size
        }

        override fun onBindViewHolder(p0: PostAdapter.MyViewHolder, p1: Int) {
            var post = res[p1]
            p0.itemView.title.text = post.title
        }
    }
}
