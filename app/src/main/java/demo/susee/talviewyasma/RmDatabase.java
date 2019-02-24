package demo.susee.talviewyasma;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import demo.susee.talviewyasma.album.offline.AlbumDao;
import demo.susee.talviewyasma.album.offline.AlbumEntity;
import demo.susee.talviewyasma.album.photo.offline.PhotoDao;
import demo.susee.talviewyasma.album.photo.offline.PhotoEntity;
import demo.susee.talviewyasma.post.offline.PostDao;
import demo.susee.talviewyasma.post.offline.PostEntity;
import demo.susee.talviewyasma.user.UserDao;
import demo.susee.talviewyasma.user.UserEntity;

@Database(entities = {PostEntity.class, AlbumEntity.class, PhotoEntity.class, UserEntity.class}, version = 3, exportSchema = false)
public abstract class RmDatabase extends RoomDatabase {
    public abstract PostDao getPostDao();

    public abstract AlbumDao getAlbumDao();

    public abstract PhotoDao getPhotoDao();

    public abstract UserDao getUserDao();

}
