package com.example.utimatetictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";

    private static final String TABLE_NAME = "users";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    //creates the table with the key
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (username TEXT primary key, password TEXT, trophies INTEGER, gamesPlayed INTEGER, wins INTEGER, losses INTEGER)");

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
        //gamesPlayed INTEGER, wins INTEGER, losses INTEGER
        values.put("gamesPlayed", 0);
        values.put("wins", 0);
        values.put("losses", 0);


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
    public int updateData(String username, int trophiesGot, boolean win)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE username=?";
        Cursor cursor = db.rawQuery(query, new String[] {username});
        cursor.moveToFirst();
        //int i = cursor.getColumnIndex("trophies");
        int totalTrophies, totalGames, totalWins;
        if(cursor != null && cursor.moveToFirst()) {
            int trophiesIndex = cursor.getColumnIndex("trophies");
            totalTrophies = cursor.getInt(trophiesIndex) + trophiesGot;

            int gamesIndex = cursor.getColumnIndex("gamesPlayed");
            totalGames = cursor.getInt(gamesIndex);

            int winsIndex = cursor.getColumnIndex("wins");
            totalWins = cursor.getInt(winsIndex);
        }
        else
            return -1;
        //int total = cursor.getInt(i) + trophiesGot;

        //trophies INTEGER, gamesPlayed INTEGER, wins INTEGER, losses INTEGER
        cv.put("trophies", totalTrophies);
        totalGames++;
        cv.put("gamesPlayed", totalGames);
        if(win){
            totalWins++;
            cv.put("wins", totalWins);
        }
        else{
            cv.put("losses", totalGames-totalWins);
        }

        int result = db.update(TABLE_NAME, cv, "username=?", new String[]{username});
        cursor.close();
        //db.close();
        if (result == -1) {
            return result;
        }
        else
            return totalTrophies;
    }

    public ArrayList<Integer> getUserData(){
        ArrayList<Integer> list = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            int i = cursor.getColumnIndex("trophies");
            list.add(cursor.getInt(i));

            i = cursor.getColumnIndex("gamesPlayed");
            list.add(cursor.getInt(i));

            i = cursor.getColumnIndex("wins");
            list.add(cursor.getInt(i));

            i = cursor.getColumnIndex("losses");
            list.add(cursor.getInt(i));
        }

        return list;
    }
}
