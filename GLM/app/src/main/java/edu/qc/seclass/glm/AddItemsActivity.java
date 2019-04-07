package edu.qc.seclass.glm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.qc.seclass.glm.Model.GList;
import edu.qc.seclass.glm.Model.Item;

public class AddItemsActivity extends AppCompatActivity {
    AppDatabase mDb;
    ItemInterface itemInterface;
    GList gList;
    ArrayList<String> listItems;
    ArrayList<Item> listItems2;
    List<Item> items;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        Spinner dropdown = findViewById(R.id.spinner1);
        listView=(ListView)findViewById(R.id.listview);
        itemName=(EditText)findViewById(R.id.itemName);
        initList(null);
        itemName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    //reset listview
                    initList((String)dropdown.getSelectedItem());
                }else {
                    //perform search
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button newItem = findViewById(R.id.button);
        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ onButtonTap(view); }
        });


        ArrayList<String> items = new ArrayList<>();
        items.add("show all categories");
        loadListWithCategories(items);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("show all categories")) {
                    itemName.setText("");
                    initList(null);
                    adapter.notifyDataSetChanged();
                }
                else{
                    itemName.setText("");
                    initList(selectedItem);
                    adapter.notifyDataSetChanged();
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(view.getContext(), EditItem.class);
                String content = (String)listView.getItemAtPosition(position);
                Item item = null;
                for(Item i:listItems2) if((i.getName()+"-("+i.getType()+")").equals(content)) item =i;
                myIntent.putExtra("ItemID", item.getId()+"");
                myIntent.putExtra("ListName", getIntent().getStringExtra("ListName"));
                startActivity(myIntent);
            }
        });
    }

    public void loadListWithCategories (ArrayList list){
        Set set = new HashSet();
        for(Item item:items){
            set.add(item.getType());
        }
        list.addAll(set);
    }
    public void onButtonTap(View view) {
        Intent myIntent = new Intent(view.getContext(), CreateNewItem.class);
        myIntent.putExtra("ListName", getIntent().getStringExtra("ListName"));
        startActivity(myIntent);
    }

    public void searchItem(String textToSearch) {
        ArrayList<String> temp = new ArrayList<>();
        for(String s : listItems) temp.add(s);
        for(String item : temp) {
            if(!item.contains(textToSearch)){
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initList(String type) {
        itemInterface = ListListScreen.getDatabase().itemInterface();
        listItems = new ArrayList<>();
        listItems2 = new ArrayList<>();
        items = itemInterface.getAllItems();
        for(Item i : items) {
            if(type == null || i.getType().equals(type)){
                listItems.add(i.getName() +"-("+ i.getType()+")");
                listItems2.add(i);
            }
        }
        adapter = new ArrayAdapter<>(this, R.layout.search_list_item, R.id.txtitem, listItems);
        listView.setAdapter(adapter);
    }
}
