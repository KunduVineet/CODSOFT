package com.teeniv.sql_database;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    EditText email, password;
    DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        email = findViewById(R.id.log_email);
        password = findViewById(R.id.log_password);
        dbHelper = new DBHelper(getApplicationContext());
    }

    public void LoginUser(View view) {
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();

        boolean loggedin = dbHelper.Login(email1,password1);
        if(loggedin)
        {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "email and password not matched", Toast.LENGTH_SHORT).show();
        }
    }
}