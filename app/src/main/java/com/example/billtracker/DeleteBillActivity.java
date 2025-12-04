package com.example.billtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeleteBillActivity extends AppCompatActivity {
    private EditText input;
    private Button cancelDelete, deleteBtn;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.delete_bill);

        input = (EditText) findViewById(R.id.edtSearch);
        cancelDelete = (Button) findViewById(R.id.cancelDeleteBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);


        dbManager = new DBManager(DeleteBillActivity.this);
        String billName = getIntent().getStringExtra("name");


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.deleteBill(billName);
                Toast.makeText(DeleteBillActivity.this, "Deleting bill from list.", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        cancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}