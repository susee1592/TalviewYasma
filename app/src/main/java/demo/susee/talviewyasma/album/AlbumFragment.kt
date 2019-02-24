package demo.susee.talviewyasma.album

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
import demo.susee.talviewyasma.album.offline.AlbumRepository
import demo.susee.talviewyasma.album.photo.PhotoListActivity
import demo.susee.talviewyasma.user.UserRepository
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumFragment : Fragment(), AlbumContract.View {
    var adapter: AlbumAdapter? = null
    var repo: AlbumRepository? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        repo = AlbumRepository(context)
        var presenter = AlbumPresenter(this, repo)
        adapter = AlbumAdapter(presenter, context)
        postList.adapter = adapter
        presenter.getAlbums()
        super.onActivityCreated(savedInstanceState)
    }

    override fun showAlbums(res: ArrayList<Result.Album>) {
        adapter?.setData(res)
    }

    override fun showNoResult() {
        Toast.makeText(context, "No Results Found!", Toast.LENGTH_SHORT).show()
    }

    override fun showError(str: String?) {
        Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show()
        repo?.allAlbums?.observe(this, Observer {
            var ite = it?.iterator()
            var res = ArrayList<Result.Album>()
            while (ite?.hasNext()!!) {
                var cr = ite?.next()
                res.add(Result.Album(cr.id, cr.userId, cr.title))
            }
            adapter?.setData(res)
        })
    }

    override fun albumPhotos(id: Int) {
        val intent = Intent(context, PhotoListActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    class AlbumAdapter(var presenter: AlbumPresenter, val context: Context?) :
        RecyclerView.Adapter<AlbumAdapter.MyViewHolder>() {
        var res: ArrayList<Result.Album> = ArrayList<Result.Album>()

        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
            return MyViewHolder(
                LayoutInflater.from(p0.context).inflate(
                    R.layout.item_album,
                    p0,
                    false
                )
            )
        }

        fun setData(result: ArrayList<Result.Album>) {
            res.addAll(result)
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return res.size
        }

        override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
            var album = res[p1]
            p0.itemView.title.text = album.title
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    val user = UserRepository(context).getUser(album.userId)
                    if (user != null) {
                        p0.itemView.name.text = user.name
                        p0.itemView.email.text = user.email
                    }
                    return null
                }
            }.execute()
            p0.itemView.setOnClickListener { presenter.albumClicked(album.id) }
        }
    }
}

