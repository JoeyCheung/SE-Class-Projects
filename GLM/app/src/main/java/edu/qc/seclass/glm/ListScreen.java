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

        EditText listTitle = (EditText)findViewById(R.id.gListName);

        loadData();

        simpleExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView2);

        listAdapter = new CustomAdapter(ListScreen.this, deptList);

        simpleExpandableListView.setAdapter(listAdapter);

        expandAll();

        listTitle.setText(getIntent().getExtras().getString("GList"));

        simpleExpandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {

            GroupInfo headerInfo = deptList.get(groupPosition);
            ChildInfo detailInfo =  headerInfo.getProductList().get(childPosition);

            return false;
        });

        simpleExpandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
					
                    long packedPos = ((ExpandableListView) parent).getExpandableListPosition(position);
                    int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);
                    int childPosition = ExpandableListView.getPackedPositionChild(packedPos);
                    GroupInfo headerInfo = deptList.get(groupPosition);
                    ChildInfo detailInfo =  headerInfo.getProductList().get(childPosition);
                    GroceryListEntry thing = AppDatabase.getInstance(getApplicationContext()).groceryListEntry();
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

        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                GroupInfo headerInfo = deptList.get(groupPosition);

                return false;
            }
        });
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
    }

    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.collapseGroup(i);
        }
    }
    
    private void loadData(){
        List<GListEntry> thing = AppDatabase.getInstance(getApplicationContext()).groceryListEntry().getAllGListEntries();
        for (GListEntry i:thing){
            if(i.getGListId().equals(getIntent().getExtras().getString("GList")))
                addProduct(AppDatabase.getInstance(getApplicationContext()).itemInterface().getAllItemsWithId(i.getItemId()).get(0).getType(),AppDatabase.getInstance(getApplicationContext()).itemInterface().getAllItemsWithId(i.getItemId()).get(0).getName(), (int)i.getQuantity(),i.getUnit(), i);
        }

    }

    private int addProduct(String department, String product, int quant, String units, GListEntry g){
        int groupPosition = 0;

        GroupInfo headerInfo = subjects.get(department);

        if(headerInfo == null){
            headerInfo = new GroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);

            Collections.sort(deptList);
        }

        ArrayList<ChildInfo> productList = headerInfo.getProductList();


        int listSize = productList.size();

        listSize++;

        ChildInfo detailInfo = new ChildInfo();
        detailInfo.setSequence("");
        detailInfo.setName(product);
        detailInfo.setQuantityAndUnit(quant+" "+units);
        productList.add(detailInfo);
        detailInfo.setCheck(g.isCheckBox());
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
                Intent myIntent = new Intent(this, AddItemsActivity.class);
                myIntent.putExtra("ListName", getIntent().getExtras().getString("GList"));
                startActivity(myIntent);
                return true;
            case R.id.menuHome:

                startActivity(new Intent(this, ListListScreen.class));
                return true;
            case R.id.menuDelete:
                AppDatabase.getInstance(getApplicationContext()).groceryListEntry().clearAllChecked(getIntent().getExtras().getString("GList"));
                Intent dintent = new Intent(this, ListScreen.class);
                dintent.putExtra("GList", getIntent().getExtras().getString("GList"));
                startActivity(dintent);
                return true;
            case R.id.menuUncheck:
                AppDatabase.getInstance(getApplicationContext()).groceryListEntry().uncheckAllForGList(getIntent().getExtras().getString("GList"));
                Intent intent = new Intent(this, ListScreen.class);
                intent.putExtra("GList", getIntent().getExtras().getString("GList"));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
