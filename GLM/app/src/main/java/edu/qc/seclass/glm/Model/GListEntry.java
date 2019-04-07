package edu.qc.seclass.glm.Model;

/**
 * GListEntry class is analogous to a single entry into a GList
 * (ie  " [ ] Potato 3 lbs " )
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity (foreignKeys = {
        @ForeignKey (
                entity = Item.class,
                parentColumns = "id",
                childColumns = "item_id"
        ),
        @ForeignKey (
                entity = GList.class,
                parentColumns = "name",
                childColumns = "g_list_id",
                onDelete = CASCADE
        )
})
public class GListEntry {

    public GListEntry(double quantity, String unit, int itemId, String gListId) {
        this.checkBox = false;
        this.quantity = quantity;
        this.unit = unit;
        this.itemId = itemId;

        this.gListId = gListId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getGListId() {
        return gListId;
    }

    public void setGListId(String gListId) {
        this.gListId = gListId;
    }



    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "id")
    private int id;

    @ColumnInfo (name = "check_box")
    private boolean checkBox;

    @ColumnInfo (name = "quantity")
    private double quantity;

    @ColumnInfo (name = "unit")
    private String unit;

    @ColumnInfo (name = "item_id")
    private int itemId;

    @ColumnInfo (name = "g_list_id")
    private String gListId;
}
