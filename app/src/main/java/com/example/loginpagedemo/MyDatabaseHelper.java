package com.example.loginpagedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "details.db";
    private static final String TABLE_NAME = "userdetails";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final int VERSION = 8;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) NOT NULL, " + EMAIL + " TEXT NOT NULL, " + USERNAME + " TEXT NOT NULL, " + PASSWORD + " TEXT NOT NULL); ";
    private static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);

            Toast.makeText(context, "onCREATE IS CALLED", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Exception" + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {
            Toast.makeText(context, "onUPGRADE IS CALLED", Toast.LENGTH_SHORT).show();

            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        } catch (Exception e) {

            Toast.makeText(context, "EXCEPTION" + e, Toast.LENGTH_SHORT).show();

        }


    }

    public long insertData(Userdetails userdetails) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, userdetails.getName());
        contentValues.put(EMAIL, userdetails.getEmail());
        contentValues.put(USERNAME, userdetails.getUsername());
        contentValues.put(PASSWORD, userdetails.getPassword());

        long rowId = -sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowId;
    }

    public Boolean findpassword(String uname,String pass)
    {

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Boolean result=false;

        if(cursor.getCount()==0)
        {
            Toast.makeText(context, "NO DATA IS FOUND", Toast.LENGTH_SHORT).show();

        }
        else
        {
            while (cursor.moveToNext())
            {
                String username=cursor.getString(3);
                String password=cursor.getString(4);

                if (username.equals(uname)&&password.equals(pass))
                {

                    result=true;
                    return result;

                }
            }

        }
return result;
    }


}
