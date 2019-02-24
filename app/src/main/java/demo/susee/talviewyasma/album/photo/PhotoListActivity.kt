package demo.susee.talviewyasma.album.photo

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import demo.susee.talviewyasma.R
import demo.susee.talviewyasma.Result
import demo.susee.talviewyasma.album.photo.offline.PhotoRepository
import kotlinx.android.synthetic.main.activity_photo_list.*
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoListActivity : AppCompatActivity(), PhotoContract.View {
    var adapter: PhotoAdapter? = null
    var repo: PhotoRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)

        val id = intent.getIntExtra("id", 0)
        repo = PhotoRepository(this)
        val presenter = PhotoPresenter(this, repo)
        adapter = PhotoAdapter(presenter)
        photoRV.layoutManager = GridLayoutManager(this, 3)
        photoRV.adapter = adapter
        swipeRefresh.post {
            swipeRefresh.isRefreshing=true
            presenter.getPhotos(id) }
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing=true
            presenter.getPhotos(id) }
    }

    override fun showPhotos(result: ArrayList<Result.Photo>) {
        swipeRefresh.isRefreshing=false
        adapter?.setData(result)
    }

    override fun showError(str: String?) {
        swipeRefresh.isRefreshing=false
        Toast.makeText(baseContext, "No Internet Connection!", Toast.LENGTH_SHORT).show()
        repo?.allAlbums?.observe(this, Observer {
            var ite = it?.iterator()
            var res = ArrayList<Result.Photo>()
            while (ite?.hasNext()!!) {
                var cr = ite?.next()
                res.add(Result.Photo(cr.id, cr.albumId, cr.title, cr.url, cr.thumbnailUrl))
            }
            adapter?.setData(res)
        })
    }

    override fun showNoResult() {
        swipeRefresh.isRefreshing=false
        Toast.makeText(baseContext, "No Results Found!", Toast.LENGTH_SHORT).show()
    }

    override fun photoDetails(url: String, itemView: View) {
        val options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, itemView, "transition")
        val revealX = (itemView.x + itemView.width / 2).toInt()
        val revealY = (itemView.y + itemView.height / 2).toInt()

        val intent = Intent(this, PhotoDetailActivity::class.java)
        intent.putExtra(PhotoDetailActivity.EXTRA_CIRCULAR_REVEAL_X, revealX)
        intent.putExtra(PhotoDetailActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY)
        intent.putExtra("EXTRA_IMAGE_URL", url)
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }

    class PhotoAdapter(val presenter: PhotoPresenter) : RecyclerView.Adapter<PhotoAdapter.MyViewHolder>() {
        var res: ArrayList<Result.Photo> = ArrayList<Result.Photo>()

        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_photo, p0, false))
        }

        fun setData(result: ArrayList<Result.Photo>) {
            res.addAll(result)
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return res.size
        }

        override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
            var photo = res[p1]
            p0.itemView.titleTV.text = photo.title
            Picasso.get().load(photo.thumbnailUrl).into(p0.itemView.image)
            p0.itemView.setOnClickListener {
                presenter.photoClicked(photo.url, p0.itemView)
            }
        }
    }
}
