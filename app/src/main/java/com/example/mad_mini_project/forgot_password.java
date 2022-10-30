package com.example.mad_mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class forgot_password extends AppCompatActivity {

    EditText email;
    Button reset;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogot_password);

        email = findViewById(R.id.forgot_password_email);
        reset = findViewById(R.id.reset_password_button_forgot);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(forgot_password.this, reset_password.class));
                validateFields();

            }
        });

    }

    private void validateFields() {
        String f_email;

        f_email = email.getText().toString();

        if(!(f_email.isEmpty())){
            if(f_email.matches(emailPattern)){
                logUser();
            }
            else {
                email.setError("Please enter a valid email address");
            }

        }
        else{
            email.setError("Please enter an email");
        }
    }

    private void logUser() {
        Intent intent = new Intent(forgot_password.this, reset_password.class);
        startActivity(intent);
    }
}