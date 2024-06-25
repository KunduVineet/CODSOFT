package com.teeniv.sql_database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "demo_db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_QUERY = "CREATE TABLE register1(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT, gender TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS register1");
        onCreate(sqLiteDatabase);
    }
    public boolean RegisteruserHelper(String name1, String email, String password, String gender)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name1);
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("gender",gender);

        long l  = sqLiteDatabase.insert("register1",null,contentValues);
        sqLiteDatabase.close();

        return l > 0;
    }

    boolean loggedin;
    @SuppressLint("Recycle")
    public boolean Login(String email, String password)
    {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM register1 WHERE email = '' "+email+" AND password ='' "+password +" ",null);
        if(cursor.moveToFirst())
        {
            loggedin = true;
        }   else {
            loggedin = false;
        }
        return loggedin;
    }
}
