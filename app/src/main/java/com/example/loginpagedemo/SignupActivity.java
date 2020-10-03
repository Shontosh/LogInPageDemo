package com.example.loginpagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nametext,emailtext,usernametext,passwordtext;
    private Button signupbutton;
    Userdetails userdetails;
MyDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nametext=findViewById(R.id.upnameedittextid);
        emailtext=findViewById(R.id.upemailedittextid);
        usernametext=findViewById(R.id.upusernameid);
        passwordtext=findViewById(R.id.uppassid);
        signupbutton=findViewById(R.id.signupbuttonid);
        databaseHelper=new MyDatabaseHelper(this);
        userdetails=new Userdetails();
        signupbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String name=nametext.getText().toString();
        String email=emailtext.getText().toString();
        String username=usernametext.getText().toString();
        String password=passwordtext.getText().toString();

        userdetails.setName(name);
        userdetails.setEmail(email);
        userdetails.setUsername(username);
        userdetails.setPassword(password);

        long rowId=databaseHelper.insertData(userdetails);
        if(rowId>0)
        {
            Toast.makeText(this, "Row "+rowId+"Successfully inserted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Row Insertion is Failed", Toast.LENGTH_SHORT).show();
        }


    }
}
