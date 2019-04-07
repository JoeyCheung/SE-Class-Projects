package edu.qc.seclass.glm;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.qc.seclass.glm.Model.GList;

@Dao
public interface GListDao {

    @Insert
    void insert(GList glist);

    @Insert
    void insertAll(GList glist);

    @Update
    void update(GList... glists);

    @Delete
    void delete(GList... glists);

    @Query("DELETE FROM GList WHERE name=:name")
    void deleteFromGListByName(String name);

    @Query ("UPDATE GList SET name=:newName WHERE name=:oldName")
    void changeGListName(String newName, String oldName);

    @Query ("SELECT * FROM GList")
    List<GList> getAllGLists();

    @Query ("SELECT * FROM GList WHERE name=:name")
    GList getGListWithName(final String name);
}
