package com.example.romantichate.sqlitedemo;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mDatabaseHelper = new DatabaseHelper(this);
        pupulateLisView();
    }

    private void pupulateLisView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            //get the value from database in column
            //then add it to the array-list
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        final ListAdapter apadter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(apadter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG,"onItemClick: You Clicked on " + name);

                Cursor data=mDatabaseHelper.getItemID(name);//get the id associated whit that name
                int itemID = 1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG,"onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ListDataActivity.this,EditDataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);
                    startActivity(editScreenIntent);

                }else{
                    toastMessage("No ID associated whit that name");
                }
            }
        });

    }

    /**
     * customisable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
