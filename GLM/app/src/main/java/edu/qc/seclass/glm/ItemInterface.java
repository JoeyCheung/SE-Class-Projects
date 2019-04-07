package edu.qc.seclass.glm;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.qc.seclass.glm.Model.Item;

public @Dao interface ItemInterface {

    @Insert
    void insert (Item item);

    @Insert
    void insertAll(Item... items);

    @Update
    void update(Item... items);

    @Delete
    void delete(Item... items);

    @Query("SELECT * FROM Item")
    List<Item> getAllItems();

    @Query ("SELECT * FROM Item WHERE id=:itemId")
    List<Item> getAllItemsWithId(final int itemId);
}
