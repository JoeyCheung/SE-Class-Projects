package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import edu.qc.seclass.glm.Model.GList;
import edu.qc.seclass.glm.Model.GListEntry;


public class EditList extends EditMenuExtendable {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        Button addList = findViewById(R.id.changeListName);
        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonTap(view);
            }
        });
    }

    public void onButtonTap(View view) {
        //Old list name
        String oldName = getIntent().getStringExtra("GList");

        //New List Name
        EditText listName = findViewById(R.id.listName);
        String newName = listName.getText().toString();


        List<GListEntry> gListEntries = AppDatabase.getInstance(getApplicationContext()).gListEntryDao().getAllGListEntries();

        try{
            AppDatabase.getInstance(getApplicationContext()).gListDao().insert(new GList(newName));
            for(GListEntry i:gListEntries){
                if(i.getGListId().equals(oldName)){
                    i.setGListId(newName);
                    GListEntry entry = new GListEntry(i.getQuantity(), i.getUnit(), i.getItemId(), i.getGListId());
                    entry.setCheckBox(i.isCheckBox());
                    AppDatabase.getInstance(getApplicationContext()).gListEntryDao().insert(entry);
                }
            }
            AppDatabase.getInstance(getApplicationContext()).gListDao().deleteFromGListByName(oldName);
            startActivity(new Intent(this, ListListScreen.class));
        }catch(Exception e){
            Toast.makeText(this, "List Name Already Exists", Toast.LENGTH_SHORT).show();
        }




        //Change Name
//        GList list = AppDatabase.getInstance(getApplicationContext()).gListDao().getGListWithName(oldName);
//
//        list.setName(newName);
//
//        AppDatabase.getInstance(getApplicationContext()).gListDao().update(list);



    }

}
