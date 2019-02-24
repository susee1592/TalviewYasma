package demo.susee.talviewyasma.album.offline;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "album")
public class AlbumEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "userId")
    int userId;

    @ColumnInfo(name = "title")
    String title;

    AlbumEntity(int id, int userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
