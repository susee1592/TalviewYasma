package demo.susee.talviewyasma.album

import demo.susee.talviewyasma.Result

interface AlbumContract{
    interface View{
        fun showAlbums(res:ArrayList<Result.Album>)

        fun showNoResult()

        fun showError(str:String?)
    }

    interface Presenter{
        fun getAlbums()
    }
}
