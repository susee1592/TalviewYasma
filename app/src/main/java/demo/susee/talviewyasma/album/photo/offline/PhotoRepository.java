package demo.susee.talviewyasma.album.photo.offline;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import demo.susee.talviewyasma.RmDatabase;

import java.util.List;

public class PhotoRepository {
    RmDatabase database;

    public PhotoRepository(Context context) {
        database = Room.databaseBuilder(context, RmDatabase.class, "dataDB").build();
    }

    public void insertPhoto(int id, int userId, String title, String url, String thumbnailUrl) {
        final PhotoEntity photo = new PhotoEntity(id, userId, title, url, thumbnailUrl);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.getPhotoDao().insertPhoto(photo);
                return null;
            }
        }.execute();
    }

    public LiveData<List<PhotoEntity>> getAllAlbums() {
        return database.getPhotoDao().getAllPhotos();
    }

    public int getSize() {
        return database.getPhotoDao().getSize();
    }

}
