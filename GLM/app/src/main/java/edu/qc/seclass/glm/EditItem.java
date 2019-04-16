package edu.qc.seclass.glm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import edu.qc.seclass.glm.Model.GList;
import edu.qc.seclass.glm.Model.GListEntry;
import edu.qc.seclass.glm.Model.Item;

public class EditItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Button addList = findViewById(R.id.addItem);
        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onButtonTap(view);
                }catch (Exception e){
                    Toast.makeText(getBaseContext(), "Unit OR Quantity can't be EMPTY ", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void onButtonTap(View view) {

        EditText unitsText = findViewById(R.id.units);
        EditText quantityText = findViewById(R.id.quantity);

        int quantity = Integer.parseInt(quantityText.getText().toString());
        String units = unitsText.getText().toString();

        String listName = getIntent().getStringExtra("ListName");
        int itemId =  Integer.parseInt(getIntent().getStringExtra("ItemID"));
        Item item = AppDatabase.getInstance(getApplicationContext()).itemInterface().getAllItemsWithId(itemId).get(0);

        List<GList> gli = AppDatabase.getInstance(getApplicationContext()).groceryList().getAllGLists();
        for(GList g : gli) {
            if(g.getName().equals(listName)){
                GListEntry e = new GListEntry(quantity, units, item.getId(), listName);
                AppDatabase.getInstance(getApplicationContext()).groceryListEntry().delete(listName, item.getId());
                AppDatabase.getInstance(getApplicationContext()).groceryListEntry().insert(e);
            }
        }

        Intent myIntent = new Intent(view.getContext(), ListScreen.class);
        myIntent.putExtra("GList", listName);
        startActivity(myIntent);

    }

}
