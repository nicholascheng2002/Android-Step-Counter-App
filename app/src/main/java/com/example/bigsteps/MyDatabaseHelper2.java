package com.example.bigsteps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper2 extends SQLiteOpenHelper {

    // important result 1 is used to check which test is taken
    // important result 2 is used to check whether the user has passed or failed the test

    private static final String DATABASE_NAME2 = "DatabaseNo2.db";
    private static final int SCHEMA_VERSION = 1;

    public MyDatabaseHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME2, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db2) {
        db2.execSQL("CREATE TABLE checklisttester ( _id2 INTEGER PRIMARY KEY AUTOINCREMENT," + " result1 INTEGER, result2 INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // read all records from checklisttester table
    public Cursor getCheck() {
        return (getReadableDatabase().rawQuery( "SELECT _id2, result1," + " result2 FROM checklisttester ORDER BY result1", null));
    }

    // write a record into checklisttester table
    public void insert2(Integer result1, Integer result2) {
        ContentValues cv2 = new ContentValues();

        cv2.put("result1", result1);
        cv2.put("result2", result2);

        getWritableDatabase().insert("checklisttester", "result1", cv2);
    }

    public Integer getresult1(Cursor b){
        return (b.getInt(1));
    }

    public Integer getresult2(Cursor b){
        return (b.getInt(2));
    }

    public Cursor allresult(){
        String selectQuery = "SELECT * FROM " + "checklisttester";
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor cursor = db2.rawQuery(selectQuery, null);
        //cursor.moveToLast();
        return cursor;
    }

}
