package edu.qc.seclass.glm;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.qc.seclass.glm.Model.GListEntry;

@Dao
public interface GroceryListEntry {

    @Query ("UPDATE GListEntry SET check_box= :gListEntryCheckbox WHERE id= :gListEntryId")
    void updateCheckbox(boolean gListEntryCheckbox, int gListEntryId);

    @Query ("DELETE FROM GListEntry WHERE check_box= 1 AND g_list_id= :gListName")
    void clearAllChecked(String gListName);

    @Query ("UPDATE GListEntry SET check_box=0 WHERE g_list_id= :gListName")
    void uncheckAllForGList(String gListName);

    @Query("DELETE FROM GListEntry WHERE g_list_id=:gListId and item_id=:itemId")
    void delete (String gListId, int itemId);

    @Insert
    void insert(GListEntry Entry);

    @Delete
    void delete(GListEntry... Entries);

    @Query ("SELECT * FROM GListEntry")
    List<GListEntry> getAllGListEntries();
}
