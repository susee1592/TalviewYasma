package demo.susee.talviewyasma.album.photo

import demo.susee.talviewyasma.Result

interface PhotoContract {
    interface View {
        fun showPhotos(res: ArrayList<Result.Photo>)

        fun showNoResult()

        fun showError(str: String?)

        fun photoDetails(url: String, itemView: android.view.View)
    }

    interface Presenter {
        fun getPhotos(id: Int)

        fun photoClicked(url: String, itemView: android.view.View)
    }
}
