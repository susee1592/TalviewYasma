package demo.susee.talviewyasma.post.offline;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PostDao {
    @Insert
    void insertPost(PostEntity entity);

    @Query("SELECT * FROM post")
    LiveData<List<PostEntity>> getAllPosts();

    @Query("SELECT COUNT(*) FROM post")
    int getSize();
}
