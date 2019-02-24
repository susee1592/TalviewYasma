package demo.susee.talviewyasma.user;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserEntity entity);

    @Query("SELECT * FROM user WHERE id=:id")
    UserEntity getUser(int id);

    @Query("SELECT COUNT(*) FROM user")
    int getSize();
}
