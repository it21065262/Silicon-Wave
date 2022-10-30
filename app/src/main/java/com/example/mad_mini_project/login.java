package com.example.mad_mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView signup_text;
    TextView forgot_text;
    TextView login_btn;
    private String passwordPattern = "[{5,20}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        signup_text = findViewById(R.id.dont_have_an_acc);
        forgot_text = findViewById(R.id.forgot_password_text);
        login_btn = findViewById(R.id.login_button);

        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, signup.class));
            }
        });

        forgot_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, forgot_password.class));
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();

            }
        });

    }

    private void validateFields() {
        String usrname, pssword;

        usrname = username.getText().toString();
        pssword = password.getText().toString();

        if(!(usrname.isEmpty())){
            if(!(pssword.isEmpty())){
                Toast.makeText(login.this,"Hello! Welcome to FoodCart",Toast.LENGTH_LONG).show();
                logUser();
            }
            else {
                password.setError("Please fill the details");
            }
        }
        else{
            username.setError("Please fill the details");
        }
    }

    private void logUser() {
        Intent intent = new Intent(login.this, menu.class);
        startActivity(intent);
    }

}
