package com.rishindrareddy.mortgagecalculatormap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RishindraReddy on 3/16/2017.
 */

public class databaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "LocData.db";
    public static final String TABLE_NAME = "LocData_TABLE";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TYPE";
    public static final String COL_3 = "STREET";
    public static final String COL_4= "CITY";
    public static final String COL_5 = "STATE";
    public static final String COL_6 = "ZIPCODE";
    public static final String COL_7 = "AMOUNT";



    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,TYPE TEXT,STREET TEXT,CITY TEXT,STATE TEXT,ZIPCODE TEXT, AMOUNT INTEGER)");

        SQLiteDatabase mydb = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,"home");
        contentValues.put(COL_3,"5146 bridgton ct");
        contentValues.put(COL_4,"dublin");
        contentValues.put(COL_5,"ca");
        contentValues.put(COL_6,"94568");
        contentValues.put(COL_7,"10000");

        long result = mydb.insert(TABLE_NAME,null ,contentValues);

        contentValues.put(COL_2,"office");
        contentValues.put(COL_3,"1 washington sq");
        contentValues.put(COL_4,"san jose");
        contentValues.put(COL_5,"ca");
        contentValues.put(COL_6,"95192");
        contentValues.put(COL_7,"100000");

         result = mydb.insert(TABLE_NAME,null ,contentValues);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String type,String street,String city,String state,String zip,String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,type);
        contentValues.put(COL_3,street);
        contentValues.put(COL_4,city);
        contentValues.put(COL_5,state);
        contentValues.put(COL_6,zip);
        contentValues.put(COL_7,amount);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
