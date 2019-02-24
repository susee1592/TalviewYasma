package demo.susee.talviewyasma.album.offline;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import demo.susee.talviewyasma.post.offline.PostEntity;

import java.util.List;

@Dao
public interface AlbumDao {
    @Insert
    void insertAlbum(AlbumEntity entity);

    @Query("SELECT * FROM album")
    LiveData<List<AlbumEntity>> getAllAlbum();

    @Query("SELECT COUNT(*) FROM album")
    int getSize();
}
