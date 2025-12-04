package com.example.billtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddBillActivity extends AppCompatActivity {
    private EditText nameEdt, dateEdt;
    private Button saveBtn, cancelBtn;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_bill_form);

        nameEdt = findViewById(R.id.billInput);
        dateEdt = findViewById(R.id.dueDateInput);
        saveBtn = findViewById(R.id.saveBillButton);
        cancelBtn = findViewById(R.id.cancelActionButton);

        dbManager = new DBManager(AddBillActivity.this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String billName = nameEdt.getText().toString();
                String dueDate = dateEdt.getText().toString();

                dbManager.addNewBill(billName, dueDate);

                Toast.makeText(AddBillActivity.this, "Bill has been saved.",
                        Toast.LENGTH_SHORT).show();

                nameEdt.setText("");
                dateEdt.setText("");

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}