package com.example.bigsteps;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class totalsteps extends AppCompatActivity {


    //inititalize MyDatabaseHelper class
    MyDatabaseHelper myDB;
    SQLiteDatabase db;
    RecyclerView recyclerviews;
    ArrayList<String> DAYS, STEPS;
    CustomAdapter customadapter;
    //Button BtnGraphs;
    GraphView graphView;

    // for graph display in totalsteps activity
    //LineGraphSeries<DataPoint> dataseries = new LineGraphSeries<>(new DataPoint[0]);

    private TabHost host;

   /* private TextView testviewers;
    private TextView testviewers2;
    private TextView testviewers3;
    private TextView testviewers4;
    private TextView testviewers5;
    private TextView testviewers6;
    private TextView testviewers7; */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totalsteps);

       // BtnGraphs = (Button) findViewById(R.id.BtnGraph);

        graphView = (GraphView) findViewById(R.id.graphs);

        recyclerviews = (RecyclerView) findViewById(R.id.recyclerview);

        myDB = new MyDatabaseHelper(totalsteps.this); // for MyDataBasehelper intiazlize
        //myDB = new MyDatabaseHelper(this); // i added
        //db = myDB.getWritableDatabase(); //i added
        db = myDB.getReadableDatabase();
       // graphView.addSeries(dataseries);
        // initialize array list
        DAYS = new ArrayList<>();
        STEPS = new ArrayList<>();

        storeDataInArrays(); // must come first before the bottom 3 line of codes
        customadapter = new CustomAdapter(totalsteps.this, DAYS, STEPS);
        recyclerviews.setAdapter(customadapter);
        recyclerviews.setLayoutManager(new LinearLayoutManager(totalsteps.this));

        host = (TabHost) findViewById(R.id.tabHost);
        host.setup();


        //Tab1
        TabHost.TabSpec spec = host.newTabSpec("This week");
        spec.setContent(R.id.tab1);
        spec.setIndicator("This week");
        host.addTab(spec);

        //Tab2
        spec = host.newTabSpec("Graph");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Graph");
        host.addTab(spec);
        host.setCurrentTab(0);

        //dataseries.resetData(grabData());

       // int g = 0;

        /*testviewers = (TextView) findViewById(R.id.testviewer);
        testviewers2 = (TextView) findViewById(R.id.testviewer2);
        testviewers3 = (TextView) findViewById(R.id.testviewer3);
        testviewers4 = (TextView) findViewById(R.id.testviewer4);
        testviewers5 = (TextView) findViewById(R.id.testviewer5);
        testviewers6 = (TextView) findViewById(R.id.testviewer6);
        testviewers7 = (TextView) findViewById(R.id.testviewer7);*/

       // displayData(g);
       // displayData();

        LineGraphSeries<DataPoint> dataseries = new LineGraphSeries<>(new DataPoint[0]);
        //graphView.getViewport().setScrollable(true);
        //graphView.getViewport().setScrollableY(true);
        dataseries.resetData(grabData());
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);
        graphView.addSeries(dataseries);

        /* graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(1);
        graphView.getViewport().setMaxX(365);

        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(1000000); */

        //function assign to AddGraph button to resume the step counting when stopped
        /* BtnGraphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LineGraphSeries<DataPoint> dataseries = new LineGraphSeries<>(new DataPoint[0]);
                //dataseries.resetData(grabData());
               // graphView.addSeries(dataseries);
            }
        }); */

    }

    private DataPoint[] grabData() {
        //SQLiteDatabase db = myDB.getReadableDatabase(); //added myself
       // String query = "SELECT * FROM " + "checklist"; //added myself
        String [] column = {"_id", "steps"};
       // Cursor cursor = myDB.readAllData(); //i add
        // test run without suppress lint
       // Cursor cursor = db.query("checklist", column, null, null, null, null, null);
         @SuppressLint("Recycle") Cursor cursor = db.query("checklist", column, null, null, null, null, null);

            DataPoint[] dataPoints = new DataPoint[cursor.getCount()];

                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    dataPoints[i] = new DataPoint(cursor.getInt(0), cursor.getInt(1)); // suppose to be get int for both but trying diff if cannot work eg getLong()
                }
                return dataPoints;
    }


      /*void displayData(int g) {
        Cursor cursor = myDB.readAllData(); //store all data in the read all object
        StringBuilder stringBuilder = new StringBuilder(); // to store data in textview
        if(cursor.getCount() == 0) {
            Toast.makeText(this,"NO DATA", Toast.LENGTH_SHORT).show(); // if no data pops up oon screen no data in the table for checking purpose only
        } else {

             while (cursor.moveToNext()){
                g++;
                stringBuilder.append("DAY" + g + "\n" + cursor.getInt(1) + " "); // for passing the data to textview from the sql table
            }
            if(g==1){
            testviewers.setText(stringBuilder);
            } else if (g==2) {
                testviewers2.setText(stringBuilder);
            } else if (g==3) {
                testviewers3.setText(stringBuilder);
            } else if (g==4){
                testviewers4.setText(stringBuilder);
            } else if (g==5){
                testviewers5.setText(stringBuilder);
            } else if (g==6){
                testviewers6.setText(stringBuilder);
            } else if (g==7){
                testviewers7.setText(stringBuilder);
            }

        }*/

         void storeDataInArrays() {
             Cursor cursor = myDB.getAll(); //store all data in the read all object
             StringBuilder stringBuilder = new StringBuilder(); // to store data in textview
             if(cursor.getCount() == 0) {
                 Toast.makeText(this,"NO DATA", Toast.LENGTH_SHORT).show(); // if no data pops up oon screen no data in the table for checking purpose only
             } else {
                 while(cursor.moveToNext()){
                     DAYS.add(cursor.getString(0));
                     STEPS.add(cursor.getString(1));
                 }
             }

    }

    @Override
    protected void onDestroy() {
             myDB.close();
        super.onDestroy();
    }
}



