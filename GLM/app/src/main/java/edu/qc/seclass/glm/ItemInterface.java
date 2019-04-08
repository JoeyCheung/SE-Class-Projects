package edu.qc.seclass.glm;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.qc.seclass.glm.Model.Item;

@Dao
public interface ItemInterface {

    @Query("SELECT * FROM Item")
    List<Item> getAllItems();

    @Query ("SELECT * FROM Item WHERE id=:itemId")
    List<Item> getAllItemsWithId(final int itemId);

    @Insert
    void insert (Item i);

    @Delete
    void delete(Item... i);
}
