package demo.susee.talviewyasma.album.photo.offline;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import demo.susee.talviewyasma.album.offline.AlbumEntity;

import java.util.List;

@Dao
public interface PhotoDao {
    @Insert
    void insertPhoto(PhotoEntity entity);

    @Query("SELECT * FROM photo")
    LiveData<List<PhotoEntity>> getAllPhotos();

    @Query("SELECT COUNT(*) FROM photo")
    int getSize();
}
