package com.example.mad_mini_project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name;
    EditText price;
    EditText url;
    Button btnAdd;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText) findViewById(R.id.txtName);
        price = (EditText) findViewById(R.id.txtPrice);
        url = (EditText) findViewById(R.id.txtImageUrl);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack);


        btnAdd.setOnClickListener(view -> {
            insertData();
            clearAll();
        });

        btnBack.setOnClickListener(view -> finish());
    }

    private void insertData(){
        Map<String, Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("price",price.getText().toString());
        map.put("url",url.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Items").push()
                .setValue(map)
                .addOnSuccessListener(unused -> Toast.makeText(AddActivity.this,"Success", Toast.LENGTH_LONG).show())
                .addOnFailureListener(e -> Toast.makeText(AddActivity.this,"Failed", Toast.LENGTH_LONG).show());
    }

    private void clearAll(){
        name.setText("");
        price.setText("");
        url.setText("");
    }

}