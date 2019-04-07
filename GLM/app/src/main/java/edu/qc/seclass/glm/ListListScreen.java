package edu.qc.seclass.glm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.qc.seclass.glm.Model.GList;

public class ListListScreen extends MenuActivity {
    private ListView simpleList;
//    private FloatingActionButton addList;
    static AppDatabase mDb;
    GListDao gListDao;
    GList glist;

    @Override
    public void onBackPressed()
    {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_list_screen);

//        addList = (FloatingActionButton) findViewById(R.id.addList);

        mDb=AppDatabase.getInstance(this);

        gListDao = mDb.gListDao();// Get DAO object
        /*glist = new GList("stuffing");// Create User object to insert
        gListDao.insert(glist); // Insert it in database*/

        //addList = (FloatingActionButton) findViewById(R.id.addList);

        final ArrayList<String> list = new ArrayList<String>();
         //gListDao.getAllGLists();
        //
        final ArrayList<GList> list2=new ArrayList<>(gListDao.getAllGLists());
        for (GList i : list2) {
            list.add(i.getName());
        }



        simpleList = (ListView)findViewById(R.id.listView);
        //final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_list_screen, R.id.textView, countryList);
        final StableArrayAdapter arrayAdapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        simpleList.setAdapter(arrayAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(view.getContext(), ListScreen.class);
                myIntent.putExtra("GList", list2.get(position).getName());
                startActivity(myIntent);
            }

        });
        simpleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /*GroupInfo headerInfo = deptList.get(position);
                ChildInfo detailInfo =  headerInfo.getProductList().get(position);
                Toast.makeText(getBaseContext(), "Long Clicked on :: " + headerInfo.getName() + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();*/
                GList item = list2.get(position);
                new AlertDialog.Builder(parent.getContext())
                        .setTitle("Edit or Delete")
                        .setMessage("Would you like to edit or delete this list?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                AppDatabase.getInstance(getApplicationContext()).gListDao().delete(item);
                                Intent myIntent = new Intent(view.getContext(), ListListScreen.class);
                                startActivity(myIntent);
                            }})
                        .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton){
                                Intent editIntent = new Intent(view.getContext(), EditList.class);
                                editIntent.putExtra("GList", item.getName());
                                startActivity(editIntent);
                            }
                        }).show();
                return true;
            }
        });


    }

    public static AppDatabase getDatabase() {
        return mDb;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}
