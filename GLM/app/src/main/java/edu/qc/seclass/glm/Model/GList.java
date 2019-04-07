package edu.qc.seclass.glm.Model;

/**
 * GList class representing a "list of entries."
 * A GList is analogous to an actual practical grocery list.
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;

@Entity
public class GList {

    public GList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PrimaryKey
    @NonNull
    private String name;
}
