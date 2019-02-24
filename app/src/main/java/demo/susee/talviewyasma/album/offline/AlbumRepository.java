package demo.susee.talviewyasma.album.offline;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import demo.susee.talviewyasma.RmDatabase;

import java.util.List;

public class AlbumRepository {
    RmDatabase database;

    public AlbumRepository(Context context) {
        database = Room.databaseBuilder(context, RmDatabase.class, "dataDB").build();
    }

    public void insertAlbum(int id, int userId, String title) {
        final AlbumEntity post = new AlbumEntity(id, userId, title);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.getAlbumDao().insertAlbum(post);
                return null;
            }
        }.execute();
    }

    public LiveData<List<AlbumEntity>> getAllAlbums() {
        return database.getAlbumDao().getAllAlbum();
    }

    public int getSize() {
        return database.getAlbumDao().getSize();
    }

}
