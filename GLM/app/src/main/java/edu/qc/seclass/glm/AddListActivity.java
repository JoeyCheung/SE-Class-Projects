package edu.qc.seclass.glm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.qc.seclass.glm.Model.GList;

public class AddListActivity extends EditMenuExtendable {

    AppDatabase mDb;
    GListDao gListDao;
    GList glist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        Button addItem = findViewById(R.id.AddButton);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonTap(view);
            }
        });

    }

    //Add GList name to Database on button tap

    public void onButtonTap(View view) {
        EditText listName = findViewById(R.id.listName);
        String name = listName.getText().toString();
        gListDao = ListListScreen.getDatabase().gListDao();
        GList gList = new GList(name);

        GList exists = AppDatabase.getInstance(getApplicationContext()).gListDao().getGListWithName(name);
        if(name.trim().length() == 0){
            Toast.makeText(getBaseContext(), "Name cannot be empty", Toast.LENGTH_LONG).show();
            return;
        } else if(exists != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(AddListActivity.this);

            builder.setCancelable(true);
            builder.setTitle("Error!");
            builder.setMessage("List name already exists");

             builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.cancel();
                 }
             });
             builder.show();
        } else {
             gListDao.insert(gList);
             startActivity(new Intent(this, ListListScreen.class));
         }
    }


    public void populateRoom (String name){
        mDb=AppDatabase.getInstance(this);
        gListDao = mDb.gListDao();// Get DAO object
        glist = new GList(name);// Create User object to insert
        gListDao.insert(glist); // Insert it in database
    }
}
