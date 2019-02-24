package demo.susee.talviewyasma;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import demo.susee.talviewyasma.post.offline.PostDao;
import demo.susee.talviewyasma.post.offline.PostEntity;

@Database(entities = {PostEntity.class}, version = 1, exportSchema = false)
public abstract class RmDatabase extends RoomDatabase {
    public abstract PostDao getPostDao();
}
