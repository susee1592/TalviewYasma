package demo.susee.talviewyasma.user;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import demo.susee.talviewyasma.RmDatabase;

public class UserRepository {
    RmDatabase database;

    public UserRepository(Context context) {
        database = Room.databaseBuilder(context, RmDatabase.class, "dataDB").build();
    }

    public void insertUser(int id, String name, String username, String email, String phone,
                           String website, Address address, Company company) {
        final UserEntity post = new UserEntity(id, name, username, email, phone,
                website, address, company);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.getUserDao().insertUser(post);
                return null;
            }
        }.execute();
    }

    public UserEntity getUser(int id) {
        return database.getUserDao().getUser(id);
    }

    public int getSize() {
        return database.getPostDao().getSize();
    }
}
