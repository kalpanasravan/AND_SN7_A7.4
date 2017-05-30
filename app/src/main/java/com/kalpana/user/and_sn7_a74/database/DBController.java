package com.kalpana.user.and_sn7_a74.database;

/**
 * Created by user on 24-05-2017.
 */

//package com.javapapers.android.sqlitestorageoption;

import java.util.ArrayList;
import java.util.HashMap;
import android.util.Log;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController  extends SQLiteOpenHelper {
    private static final String LOGCAT = null;

    public DBController(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 1);
        Log.d(LOGCAT,"Created");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE employee ( Id INTEGER PRIMARY KEY, FirstName TEXT,LastName TEXT)";
        database.execSQL(query);
        Log.d(LOGCAT,"employees Created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS employee";
        database.execSQL(query);
        onCreate(database);
    }

    public void insertEmployees(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("FirstName", queryValues.get("FirstName"));
        values.put("LastName",queryValues.get("LastName"));
        database.insert("employees", null, values);
        database.close();
    }

    public int updateEmployees(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("FirstName", queryValues.get("FirstName"));
        values.put("LastName",queryValues.get("LastName"));
        return database.update("employee", values, "Id" + " = ?", new String[] { queryValues.get("Id") });
        //String updateQuery = "Update  words set txtWord='"+word+"' where txtWord='"+ oldWord +"'";
        //Log.d(LOGCAT,updateQuery);
        //database.rawQuery(updateQuery, null);
        //return database.update("words", values, "txtWord  = ?", new String[] { word });
    }

    public void deleteEmployees(String id) {
        Log.d(LOGCAT,"delete");
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM  employee where Id='"+ id +"'";
        Log.d("query",deleteQuery);
        database.execSQL(deleteQuery);
    }

    public ArrayList<HashMap<String, String>> getAllEmployees() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM employee";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Id", cursor.getString(0));
                map.put("FirstName", cursor.getString(1));
                map.put("LastName",cursor.getString(2));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        // return contact list
        return wordList;
    }

    public HashMap<String, String> getAnimalInfo(String id) {
        HashMap<String, String> wordList = new HashMap<String, String>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM employees where Id='"+id+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //HashMap<String, String> map = new HashMap<String, String>();
                wordList.put("FirstName", cursor.getString(1));
                //wordList.add(map);
            } while (cursor.moveToNext());
        }
        return wordList;
    }
}
