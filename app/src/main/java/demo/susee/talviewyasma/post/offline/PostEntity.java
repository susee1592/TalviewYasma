package demo.susee.talviewyasma.post.offline;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "post")
public class PostEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "userId")
    int userId;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "body")
    String body;

    PostEntity(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
