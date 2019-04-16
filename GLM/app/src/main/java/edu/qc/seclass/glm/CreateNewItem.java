package edu.qc.seclass.glm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import edu.qc.seclass.glm.Model.Item;

public class CreateNewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);

        Button addItem = findViewById(R.id.newItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonTap(view);
            }
        });
    }

    public void onButtonTap(View view) {
        EditText nameIn = findViewById(R.id.name);
        EditText typeIn = findViewById(R.id.type);

        String name = nameIn.getText().toString();
        String type = typeIn.getText().toString();

        if (name.trim().length() == 0 || type.trim().length() == 0) {
            Toast.makeText(getBaseContext(), "Item must have a name and type!", Toast.LENGTH_LONG).show();
            return;
        }

        Item item = new Item(name, type);

        List<Item> items = AppDatabase.getInstance(getApplicationContext()).itemInterface().getAllItems();
        boolean exists = false;

        for(Item i:items){
            if(i.getName().equals(item.getName()) && i.getType().equals(item.getType())){
                exists = true;
            }
        }
        if(exists){
            Toast.makeText(getBaseContext(), "Item already exists!", Toast.LENGTH_LONG).show();
        } else {
            AppDatabase.getInstance(getApplicationContext()).itemInterface().insert(item);
            items = AppDatabase.getInstance(getApplicationContext()).itemInterface().getAllItems();
            for(Item i:items){
                if(i.getName().equals(item.getName()) && i.getType().equals(item.getType())){
                    item = i;
                }
            }

            Intent myIntent = new Intent(view.getContext(), EditItem.class);
            myIntent.putExtra("ItemID", item.getId()+"");
            myIntent.putExtra("ListName", getIntent().getStringExtra("ListName"));
            startActivity(myIntent);
        }

    }

}
