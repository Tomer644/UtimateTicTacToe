package com.example.utimatetictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
        db.execSQL("create table users (username TEXT primary key, password TEXT, trophies INTEGER, gamesPlayed INTEGER, wins INTEGER, losses INTEGER, skins_own INTEGER, user_pfps INTEGER)");

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
        values.put("skins_own", 2);
        //int user_pfps
        values.put("user_pfps", 0);

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

    public boolean buySkin(String username, int price){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE username=?";
        Cursor cursor = db.rawQuery(query, new String[] {username});

        int currentTrophies, currentSkins;
        if(cursor != null && cursor.moveToFirst()) {
            int trophiesIndex = cursor.getColumnIndex("trophies");
            currentTrophies = cursor.getInt(trophiesIndex);

            int skinsIndex = cursor.getColumnIndex("skins_own");
            currentSkins = cursor.getInt(skinsIndex);
        }
        else return false;

        if(currentTrophies<price)
            return false;

        currentTrophies-=price;
        cv.put("trophies", currentTrophies);
        currentSkins++;
        cv.put("skins_own", currentSkins);
        int result = db.update(TABLE_NAME, cv, "username=?", new String[]{username});
        cursor.close();

        if (result == -1)
            return false;
        return true;
    }

    public ArrayList<Integer> getUserData(String username){
        ArrayList<Integer> list = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where username=?",new String[] {username});

        if(cursor != null && cursor.moveToFirst()) {
            int i = cursor.getColumnIndex("trophies");
            list.add(cursor.getInt(i));

            i = cursor.getColumnIndex("gamesPlayed");
            list.add(cursor.getInt(i));

            i = cursor.getColumnIndex("wins");
            list.add(cursor.getInt(i));

            i = cursor.getColumnIndex("losses");
            list.add(cursor.getInt(i));

            i = cursor.getColumnIndex("skins_own");
            list.add(cursor.getInt(i));

            i = cursor.getColumnIndex("user_pfps");
            list.add(cursor.getInt(i));
        }
        return list;
    }

    public boolean deleteUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, "username=?", new String[]{username});
        if(res==-1){ return false;}
        else{ return true; }
    }

    public boolean userUploadedPfp(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String query = "SELECT user_pfps FROM "+TABLE_NAME+" WHERE username=?";
        Cursor cursor = db.rawQuery(query, new String[] {username});

        if(cursor != null && cursor.moveToFirst()) {
            int i = cursor.getColumnIndex("user_pfps");
            int pfps = (cursor.getInt(i));
            pfps++;
            cv.put("user_pfps", pfps);
        }
        //else return false;

        int result = db.update(TABLE_NAME, cv, "username=?", new String[]{username});
        cursor.close();

        if (result == -1)
            return false;
        return true;
    }
}
