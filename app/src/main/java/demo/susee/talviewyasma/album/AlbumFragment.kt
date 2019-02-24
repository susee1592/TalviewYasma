package demo.susee.talviewyasma.album

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import demo.susee.talviewyasma.R
import demo.susee.talviewyasma.Result
import demo.susee.talviewyasma.album.photo.PhotoListActivity
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumFragment : Fragment(), AlbumContract.View {
    var adapter: AlbumAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var presenter = AlbumPresenter(this)
        adapter = AlbumAdapter(presenter)
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
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }

    override fun albumPhotos(id: Int) {
        val intent = Intent(context, PhotoListActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    class AlbumAdapter(var presenter: AlbumPresenter) : RecyclerView.Adapter<AlbumAdapter.MyViewHolder>() {
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
            p0.itemView.setOnClickListener { presenter.albumClicked(album.id) }
        }
    }
}

