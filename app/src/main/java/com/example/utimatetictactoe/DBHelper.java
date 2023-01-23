package com.example.utimatetictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";

    private static final String TABLE_NAME = "users";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    //creates the table with the key
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (username TEXT primary key, password TEXT, trophies INTEGER)");

    }

    //checks if the table exists in the device
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
    }

    //inserts the users data when we register
    public boolean insertData(String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username",username);
        values.put("password",password);
        values.put("trophies", 0);

        long result= db.insert(TABLE_NAME,null, values);
        if(result==-1)
            return false;
        else
            return  true;
    }

    //checks that we don't have 2 same usernames when we register
    public boolean checkusername(String username) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?",new String[] {username });
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //checks that the user exists when we login
    public boolean checkusernamepassword (String username, String password) {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?",new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    /* update db for:
     * win
     * lose
     * buy skin
     */
    public int updateData(String username, int trophiesGot)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE username=?";
        Cursor cursor = db.rawQuery(query, new String[] {username});
        cursor.moveToFirst();
        //int i = cursor.getColumnIndex("trophies");
        int total;
        if(cursor != null && cursor.moveToFirst()) {
            int i = cursor.getColumnIndex("trophies");
            total = cursor.getInt(i) + trophiesGot;
        }
        else
            return -1;
        //int total = cursor.getInt(i) + trophiesGot;
        cv.put("trophies", total);

        int result = db.update(TABLE_NAME, cv, "username=?", new String[]{username});
        cursor.close();
        //db.close();
        if (result == -1) {
            return result;
        }
        else
            return total;
    }
}

