package edu.qc.seclass.glm.Model;

/** Item class representing an entry in our
 * "Item dictionary."
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;

import android.support.annotation.NonNull;

@Entity (indices = {@Index (value = {"name", "type"}, unique = true)})
public class Item {

    public Item(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @PrimaryKey (autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo (name = "name")
    private String name;

    @NonNull
    @ColumnInfo (name = "type")
    private String type;

}
