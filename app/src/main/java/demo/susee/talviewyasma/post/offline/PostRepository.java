package demo.susee.talviewyasma.post.offline;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import demo.susee.talviewyasma.RmDatabase;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    RmDatabase database;

    public PostRepository(Context context) {
        database = Room.databaseBuilder(context, RmDatabase.class, "dataDB").build();
    }

    public void insertPost(int id, int userId, String title, String body) {
        final PostEntity post = new PostEntity(id, userId, title, body);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.getPostDao().insertPost(post);
                return null;
            }
        }.execute();
    }

    public LiveData<List<PostEntity>> getAllPosts() {
        return database.getPostDao().getAllPosts();
    }

    public int getSize() {
        return database.getPostDao().getSize();
    }
}
