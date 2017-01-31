package com.evilmem.albert.evilmemory.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Albert on 28/01/2017.
 */

public class LoginHelper extends SQLiteOpenHelper{
    //Declaracion del nombre de la base de datos
    public static final int DATABASE_VERSION = 1;

    //Declaracion global de la version de la base de datos
    public static final String DATABASE_NAME = "Login";

    //Declaracion del nombre de la tabla
    public static final String LOGIN_TABLE ="Users";

    //sentencia global de cracion de la base de datos
    public static final String LOGIN_TABLE_CREATE = "CREATE TABLE " + LOGIN_TABLE + " (name TEXT PRIMARY KEY UNIQUE, password INTEGER, completename TEXT, address TEXT,score4 INTEGER, score6 INTEGER, score8 INTEGER);";
    //public static final String LOGIN_TABLE_CREATE = "CREATE TABLE " + LOGIN_TABLE + " (name TEXT PRIMARY KEY UNIQUE, password INTEGER,
    //          completename TEXT, profileimage ??,score4 INTEGER, score6 INTEGER, socre8 INTEGER);";



    public LoginHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LOGIN_TABLE_CREATE);

    }


    public Cursor getUserPassName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"password"};
        String[] where = {name};
        Cursor c = db.query(
                LOGIN_TABLE,  // The table to query
                columns,         // The columns to return
                "name=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        return c;
    }

    public Cursor getUserData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"completename","address","score4","score6","score8"};
        String[] where = {name};
        Cursor c = db.query(
                LOGIN_TABLE,  // The table to query
                columns,         // The columns to return
                "name=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        return c;
    }





    public void createUser (ContentValues values, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                tableName,
                null,
                values);
    }



    public Cursor getscore4(String name){
        SQLiteDatabase db= this.getReadableDatabase();
        String [] columns = {"score4"};
        String [] where = {name};
        Cursor c= db.query(
                LOGIN_TABLE,
                columns,
                "name=?",
                where,
                null,
                null,
                null
        );
        return c;
    }

    public Cursor getscore6(String name){
        SQLiteDatabase db= this.getReadableDatabase();
        String [] columns = {"score4"};
        String [] where = {name};
        Cursor c= db.query(
                LOGIN_TABLE,
                columns,
                "name=?",
                where,
                null,
                null,
                null
        );
        return c;
    }

    public Cursor getscore8(String name){
        SQLiteDatabase db= this.getReadableDatabase();
        String [] columns = {"score4"};
        String [] where = {name};
        Cursor c= db.query(
                LOGIN_TABLE,
                columns,
                "name=?",
                where,
                null,
                null,
                null
        );
        return c;
    }

    public void DeleteRanking4(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + LOGIN_TABLE +" SET score4=" + 1);
        }

    public void DeleteRanking6(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " +LOGIN_TABLE +" SET score6=" + 1);
    }

    public void DeleteRanking8(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " +LOGIN_TABLE +" SET score8=" + 1);
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE);
        db.execSQL(LOGIN_TABLE_CREATE);
    }
}
