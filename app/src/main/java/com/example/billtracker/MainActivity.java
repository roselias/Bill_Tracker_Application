package com.example.billtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton AddFab, AddBillFab, deleteBillFab;

    Boolean isAllFabsVisible;

    ArrayList<Bills> dbData;
    private DBManager dbManager;
    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        dbData = new ArrayList<>();
        dbManager = new DBManager(MainActivity.this);
        dbData = dbManager.readBills();
        listViewAdapter = new ListViewAdapter(this,dbData);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(listViewAdapter);


        AddFab = findViewById(R.id.add_fab);
        AddBillFab = findViewById(R.id.add_Bill_fab);
        deleteBillFab = findViewById(R.id.delete_Bill_fab);

        AddBillFab.setVisibility(View.GONE);
        deleteBillFab.setVisibility(View.GONE);

        isAllFabsVisible = false;

        AddFab.setOnClickListener(view ->{
            if(!isAllFabsVisible){
                AddBillFab.show();
                deleteBillFab.show();
                isAllFabsVisible = true;
            }else {
                AddBillFab.hide();
                deleteBillFab.hide();
                isAllFabsVisible = false;
            }
        });

    }
    public void buttonOnClick(View v){
        Intent i = new Intent(getApplicationContext(), AddBillActivity.class);
        startActivity(i);
    }

    public void buttonOnClickDelete(View v){
        Intent i = new Intent(getApplicationContext(), DeleteBillActivity.class);
        startActivity(i);
    }
}