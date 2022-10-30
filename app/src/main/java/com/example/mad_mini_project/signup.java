package com.example.mad_mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText p_number;
    EditText address;
    EditText password;
    EditText c_password;
    TextView login_text;
    Button btn_signup;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        p_number = findViewById(R.id.signup_phone_number);
        address = findViewById(R.id.signup_address);
        password = findViewById(R.id.signup_password);
        c_password = findViewById(R.id.signup_confirm_password);

        btn_signup = findViewById(R.id.btn_sign_up);
        login_text = findViewById(R.id.already_have_an_acc);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this, login.class));
            }
        });

    }

}