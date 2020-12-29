package com.example.bigsteps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
   // private Context context; // changing way on inserting and reading data
    private static final String DATABASE_NAME = "DatabaseNo1.db";
    //private static final int DATABASE_VERSION = 1; // changing way of inserting and reading data
    private static final int SCHEMA_VERSION = 1;

    //for first table of sqlite database
   // private static final String TABLE_NAME = "checklist"; // table name // changing way of inserting and reading data
    //private static final String COLUMN_ID = "_id"; // changing way of inserting and reading data
    //private static final String COLUMN_STEP = "steps"; // changing way of inserting and reading dataa

    public MyDatabaseHelper(@Nullable Context context) {
       // super(context, DATABASE_NAME, null, DATABASE_VERSION); // changing way of inserting and reading data
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
       // this.context = context; //SUPER IMPORTANT LINE BECAUSE OF THIS I LOST 2 HOURS // changing way of inserting and reading data
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      //  String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_STEP + " INTEGER);"; // changing way of inserting and reading data
       // db.execSQL(query); // changing way of inserting and reading data
        db.execSQL("CREATE TABLE checklist ( _id INTEGER PRIMARY KEY AUTOINCREMENT," + " steps INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
      //  db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); // changing way of inserting and reading data
       // onCreate(db); // changing way of inserting and reading data
    }

    public Cursor getAll() {
        return (getReadableDatabase().rawQuery("SELECT _id, steps" + " FROM checklist ORDER BY _id", null));
    }

    public void insert(/*Integer _id,*/ Integer steps) {
        ContentValues cv = new ContentValues();

        //cv.put("_id", _id); // not needed because is auto increment already
        cv.put("steps", steps);

        getWritableDatabase().insert("checklist", "steps", cv);
    }

    public Integer getDay (Cursor c){
        return (c.getInt(0));
    }

    public Integer getSteps (Cursor c){
        return (c.getInt(1));
    }

    /* changing way on insert and read data
    //method for adding data of steps to first sqlite table
    //parameters to store data of each column
    void addData(Integer steps) {
        SQLiteDatabase db = this.getWritableDatabase(); //calling sqliteopenhelper class
        ContentValues cv = new ContentValues(); // store all the data of the steps taken daily on mainactivity page

        //to put data into each column
       // cv.put(COLUMN_STEP, steps);
        cv.put(COLUMN_STEP, steps);

        //to put data into the table assigned
        //result var is used only to show whether a not if the data is saved successfully
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    } */

    // changing way of insert and read data
/*
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    } */

}
