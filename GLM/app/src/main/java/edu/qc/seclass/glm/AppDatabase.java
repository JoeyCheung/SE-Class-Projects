package edu.qc.seclass.glm;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Database;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.qc.seclass.glm.Model.GList;
import edu.qc.seclass.glm.Model.GListEntry;
import edu.qc.seclass.glm.Model.Item;

@Database (entities = {Item.class, GList.class, GListEntry.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();

    public abstract GListDao gListDao();

    public abstract GListEntryDao gListEntryDao();

    private static AppDatabase INSTANCE;
    public static ExecutorService es;

    public synchronized static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            es = Executors.newSingleThreadScheduledExecutor();
            INSTANCE = getAppDatabase(context);
        }
        return INSTANCE;
    }

    public static AppDatabase getAppDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "glistDB")
                .allowMainThreadQueries().build();

    }


}

