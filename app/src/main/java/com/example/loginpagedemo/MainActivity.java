package com.example.loginpagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyDatabaseHelper myDatabaseHelper;
    private Button signinbutton, signupherebutton;
    private EditText usernameedittext, passwordedittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signinbutton = findViewById(R.id.signinbuttonid);
        signupherebutton = findViewById(R.id.signupherebuttonid);
        usernameedittext = findViewById(R.id.signinusernameid);
        passwordedittext = findViewById(R.id.signinpassid);

        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        signinbutton.setOnClickListener(this);
        signupherebutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String username = usernameedittext.getText().toString();
        String password = passwordedittext.getText().toString();

        if (view.getId() == R.id.signinbuttonid) {
            Boolean result = myDatabaseHelper.findpassword(username, password);
            if (result == true) {
                Intent intent = new Intent(MainActivity.this, Welcomepage.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Username & Psasword didn't match", Toast.LENGTH_SHORT).show();

            }

        } else if (view.getId() == R.id.signupherebuttonid) {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);

        }


    }
}
