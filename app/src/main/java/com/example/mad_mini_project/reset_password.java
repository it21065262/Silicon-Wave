package com.example.mad_mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class reset_password extends AppCompatActivity {

    EditText password;
    EditText c_password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        password = findViewById(R.id.reset_password_password);
        c_password = findViewById(R.id.reset_password_c_password);
        button = findViewById(R.id.reset);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();
            }
        });

    }

    private void validateFields() {
        String pass, c_pass;

        pass = password.getText().toString();
        c_pass = c_password.getText().toString();

        if(!(pass.isEmpty())){
            if(!(c_pass.isEmpty())){
                if (pass.matches(c_pass)){
                    Toast.makeText(reset_password.this,"Password Reset Successful",Toast.LENGTH_LONG).show();
                    logUser();

                }
                else{
                    c_password.setError("Do not match the passwords");
                }

            }
            else {
                c_password.setError("Please fill the details");
            }
        }
        else{
            password.setError("Please fill the details");
        }
    }

    private void logUser() {
        Intent intent = new Intent(reset_password.this, login.class);
        startActivity(intent);
    }


}