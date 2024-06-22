package com.teeniv.sql_database;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText reg_name, reg_email, reg_password, reg_gender;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        reg_email = findViewById(R.id.reg_email);
        reg_gender = findViewById(R.id.reg_gender);
        reg_name = findViewById(R.id.reg_name);
        reg_password = findViewById(R.id.reg_password);

        dbHelper = new DBHelper(getApplicationContext());
    }

    public void RegisterUser(View view) {
        String name = reg_name.getText().toString();
        String email = reg_email.getText().toString();
        String password = reg_password.getText().toString();
        String gender = reg_gender.getText().toString();

       boolean b =  dbHelper.RegisteruserHelper(name, email,password,gender);

       if(b)
       {
           Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show();

           reg_password.setText("");
           reg_name.setText("");
           reg_gender.setText("");
           reg_email.setText("");
       }
       else
       {
           Toast.makeText(this, "Error ..!!!", Toast.LENGTH_SHORT).show();
       }

    }
}