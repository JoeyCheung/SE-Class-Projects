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

        String o = getIntent().getStringExtra("GList");

        EditText l = findViewById(R.id.listName);
        String n = l.getText().toString();


        List<GListEntry> gListEntries = AppDatabase.getInstance(getApplicationContext()).groceryListEntry().getAllGListEntries();

        try{
            AppDatabase.getInstance(getApplicationContext()).groceryList().insert(new GList(n));
            for(GListEntry i:gListEntries){
                if(i.getGListId().equals(o)){
                    i.setGListId(n);
                    GListEntry entry = new GListEntry(i.getQuantity(), i.getUnit(), i.getItemId(), i.getGListId());
                    entry.setCheckBox(i.isCheckBox());
                    AppDatabase.getInstance(getApplicationContext()).groceryListEntry().insert(entry);
                }
            }
            AppDatabase.getInstance(getApplicationContext()).groceryList().deleteFromGListByName(o);
            startActivity(new Intent(this, ListListScreen.class));
        }catch(Exception e){
            Toast.makeText(this, "List Name Already Exists", Toast.LENGTH_SHORT).show();
        }
    }
}
