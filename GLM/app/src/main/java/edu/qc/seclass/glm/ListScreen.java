package edu.qc.seclass.glm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import edu.qc.seclass.glm.Model.GList;
import edu.qc.seclass.glm.Model.GListEntry;

public class ListScreen extends AppCompatActivity {

    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();

    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(),ListListScreen.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);

        // connect variable to List title
        EditText listTitle = (EditText)findViewById(R.id.gListName);

        // add data for displaying in expandable list view
        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView2);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(ListScreen.this, deptList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the Groups
        expandAll();

        //Put correct List title to the top of list
        listTitle.setText(getIntent().getExtras().getString("GList"));

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            //get the group header
            GroupInfo headerInfo = deptList.get(groupPosition);
            //get the child info
            ChildInfo detailInfo =  headerInfo.getProductList().get(childPosition);
            //display it or do something with it
            //Toast.makeText(getBaseContext(), " Clicked on :: " + headerInfo.getName() + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
            return false;
        });

        simpleExpandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /*GroupInfo headerInfo = deptList.get(position);
                ChildInfo detailInfo =  headerInfo.getProductList().get(position);
                Toast.makeText(getBaseContext(), "Long Clicked on :: " + headerInfo.getName() + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();*/
                if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    //final ExpandableListAdapter adapter = ((ExpandableListView) parent).getExpandableListAdapter();
                    long packedPos = ((ExpandableListView) parent).getExpandableListPosition(position);
                    int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);
                    int childPosition = ExpandableListView.getPackedPositionChild(packedPos);
                    GroupInfo headerInfo = deptList.get(groupPosition);
                    ChildInfo detailInfo =  headerInfo.getProductList().get(childPosition);
                    GListEntryDao thing = AppDatabase.getInstance(getApplicationContext()).gListEntryDao();
                    new AlertDialog.Builder(parent.getContext())
                            .setTitle("Edit or Delete")
                            .setMessage("Would you like to edit or delete this item?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("want to delete", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    thing.delete(detailInfo.getGListEntry());
                                    Intent myIntent = new Intent(view.getContext(), ListScreen.class);
                                    myIntent.putExtra("GList", (getIntent().getExtras().getString("GList")));
                                    startActivity(myIntent);
                                }})
                            .setNegativeButton("want to edit", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton){
                                    Intent myIntent = new Intent(view.getContext(), EditItem.class);
                                    myIntent.putExtra("GListString", (getIntent().getExtras().getString("GList")));
                                    String gListId = detailInfo.getGListEntry().getGListId();
                                    myIntent.putExtra("ListName", gListId);
                                    myIntent.putExtra("ItemID", detailInfo.getGListEntry().getItemId()+"");
                                    startActivity(myIntent);
                                }
                            }).show();
                }
                return false;
            }
        });

        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //display it or do something with it
                //Toast.makeText(getBaseContext(), " Header is :: " + headerInfo.getName(), Toast.LENGTH_LONG).show();

                return false;
            }
        });
    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.collapseGroup(i);
        }
    }

    //load some initial data into out list
    
    private void loadData(){
        List<GListEntry> thing = AppDatabase.getInstance(getApplicationContext()).gListEntryDao().getAllGListEntries();
        for (GListEntry i:thing){
            if(i.getGListId().equals(getIntent().getExtras().getString("GList")))
                addProduct(AppDatabase.getInstance(getApplicationContext()).itemInterface().getAllItemsWithId(i.getItemId()).get(0).getType(),AppDatabase.getInstance(getApplicationContext()).itemInterface().getAllItemsWithId(i.getItemId()).get(0).getName(), (int)i.getQuantity(),i.getUnit(), i);
        }

    }

//    public String getCurrentList() {
//        String listid = intent.getExtras().getString("GList");
//        String name = "";
//        List<GList> list = AppDatabase.getInstance(getApplicationContext()).gListDao().getAllGLists();
//        for(GList g : list) {
//            if(g.getName().equals(listid))
//                name = g.getName();
//        }
//        return name;
//    }



    //here we maintain our products in various departments
    private int addProduct(String department, String product, int quant, String units, GListEntry g){
        int groupPosition = 0;
        //check the hash map if the group already exists
        GroupInfo headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new GroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
            //sort the array list
            Collections.sort(deptList);
        }

            //get the children for the group
        ArrayList<ChildInfo> productList = headerInfo.getProductList();

        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;
        //create a new child and add that to the group
        ChildInfo detailInfo = new ChildInfo();
        //detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setSequence("");
        detailInfo.setName(product);
        detailInfo.setQuantityAndUnit(quant+" "+units);
        productList.add(detailInfo);
        detailInfo.setCheck(g.isCheckBox());
        //find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        ChildInfo p = headerInfo.containsProduct(product);
        p.setGListEntry(g);
        return groupPosition;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.commonmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id) {
            case R.id.menuAdd:
                //Toast.makeText(this, "Add menu is clicked", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(this, AddItemsActivity.class);
                myIntent.putExtra("ListName", getIntent().getExtras().getString("GList"));
                startActivity(myIntent);
                return true;
            case R.id.menuHome:
//                Intent hIntent = new Intent(this, ListScreen.class);
//                hIntent.putExtra("GList", getIntent().getExtras().getString("GList"));
//                startActivity(hIntent);
                startActivity(new Intent(this, ListListScreen.class));
                //Toast.makeText(this, "Home menu is clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuDelete:
                //Toast.makeText(this, "Unchecked menu item Clicked", Toast.LENGTH_SHORT).show();
                AppDatabase.getInstance(getApplicationContext()).gListEntryDao().clearAllChecked(getIntent().getExtras().getString("GList"));
                Intent dintent = new Intent(this, ListScreen.class);
                dintent.putExtra("GList", getIntent().getExtras().getString("GList"));
                startActivity(dintent);
                return true;
            case R.id.menuUncheck:
                //Toast.makeText(this, "Unchecked menu item Clicked", Toast.LENGTH_SHORT).show();
                AppDatabase.getInstance(getApplicationContext()).gListEntryDao().uncheckAllForGList(getIntent().getExtras().getString("GList"));
                Intent intent = new Intent(this, ListScreen.class);
                intent.putExtra("GList", getIntent().getExtras().getString("GList"));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
