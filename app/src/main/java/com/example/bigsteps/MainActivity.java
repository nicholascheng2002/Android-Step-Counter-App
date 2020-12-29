package com.example.bigsteps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;
import android.content.Context;
import androidx.cursoradapter.widget.CursorAdapter;

import java.util.Calendar;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

// Hi to whoever is reading my code i wish you the best of luck. - Nicholas 25/7/2020 : "I don't understand what i am writing"

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener, StepsListener {

    //the ids of each card view are test_cardview, checklist_cardview, totalsteps_cardview, map_cardview respectively
    public CardView test_card;
    public CardView checklist_card;
    public CardView totalsteps_card;
    public CardView map_card;

    //for making the add button work once a day only
    private boolean firstTimeUsed = false;
    private String firstTimeUsedKey = "FIRST_TIME";
    private String sharedPreferencesKey = "MY_PREF";
    private String buttonClickedKey = "BUTTON_CLICKED";
    private SharedPreferences mPrefs;
    private long savedDate = 0;

    //codes related to the home page step counter using accelerometer sensor
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    public int numSteps;
    public TextView TvSteps;
    private Button BtnStart;
    private Button BtnStop;
    private Button BtnAdds;
    private Button BtnResumes;
    private EditText steps_values; //this is currently here for testing if anything goes wrong will remove in the future
    private Cursor model = null; // for inserting data into sqlite
    private MyDatabaseHelper helper = null; // for inserting data into sqlite

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defining cards
        test_card = (CardView) findViewById(R.id.test_cardview);
        checklist_card = (CardView) findViewById(R.id.checklist_cardview);
        totalsteps_card = (CardView) findViewById(R.id.totalsteps_cardview);
        map_card = (CardView) findViewById(R.id.map_cardview);

        //assign onclicklistener to every of my object
        test_card.setOnClickListener(this);
        checklist_card.setOnClickListener(this);
        totalsteps_card.setOnClickListener(this);
        map_card.setOnClickListener(this);

        //get an instance of the SensorManager for the home page
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        //defining widgets related to the home page step counter
        TvSteps = (TextView) findViewById(R.id.TvStepss);
        BtnStart = (Button) findViewById(R.id.BtnStarts);
        BtnStop = (Button) findViewById(R.id.BtnStops);
        BtnAdds = (Button) findViewById(R.id.BtnAdd);
        BtnResumes = (Button) findViewById(R.id.BtnResume);
       // steps_values = (EditText) findViewById(R.id.steps_value); // this is currently here for testing only will remove in the future

        //initializing declared String and boolean to set global variable in onCreate() for add button
        mPrefs = getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE);
        savedDate = mPrefs.getLong(buttonClickedKey, 0);
        firstTimeUsed = mPrefs.getBoolean(firstTimeUsedKey, true);//default is true if no value is saved
        checkPrefs();

        // for inserting data into sqlite database
        helper = new MyDatabaseHelper(this);
        model = helper.getAll();


        //function assign to resume button to resume the step counting when stopped
        BtnResumes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST); // related to step counter to be added in test_10
            }
        });

        //function assign to add button to add one day's steps into sqlite database
        BtnAdds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pop up box that confirms with the user wether if they really want to add their steps walked for today into the database
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setMessage("Are you really done walking for today? You cannot undo once you submit your steps until next day!" +
                        "Please Resume if you clicked OK")
                        .setCancelable(true)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                 sensorManager.unregisterListener(MainActivity.this); // stop the step counter sensor
                                //int testStepvalue;
                                //testStepvalue = numSteps;
                                 TvSteps.setText(String.valueOf(numSteps));
                                // when add button clicked data for steps taken will be passed onto sqlite table.

                               // changing way of inserting data
                               // MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this); // data from main activity
                               // myDB.addData(Integer.valueOf(TvSteps.getText().toString().trim())); //calling add data method for sqlite database

                                // new method of inserting data into sqlite
                                helper.insert(numSteps);
                                model = helper.getAll(); // update cursor after new record is added

                                //myDB.addData(testStepvalue);

                               // saveClickedTime(); // function to save date and check if button has been pressed before
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("ALERT");
                alert.show();

                /* THE CODE IN THIS SECTION IS CURRENTLY UNUSED HOWEVER IF NEEDED IN FUTURE CAN BE UNCOMMENTED
                //following real time day to decide which column to store SQLITE data for steps in a day
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);

                switch (day) {
                    case Calendar.SUNDAY:
                        // Current day is Sunday
                        break;
                    case Calendar.MONDAY:
                        // Current day is Monday
                        break;
                    case Calendar.TUESDAY:
                        //
                        break;
                    case Calendar.WEDNESDAY:
                        //
                        break;
                    case Calendar.THURSDAY:
                        //
                        break;
                    case Calendar.FRIDAY:
                        //
                        break;
                    case Calendar.SATURDAY:

                        break;
                } */

            }
        });



        //function assign to start button it will start the step counter
        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                numSteps = 0;
                sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST); //set the sensor to work

            }
        });

        //function assign to stop button it will stop the step counter
        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                sensorManager.unregisterListener(MainActivity.this); // set the sensor to not work

            }
        });

    }

    private void saveClickedTime(){

        SharedPreferences.Editor mEditor = mPrefs.edit();
        Calendar cal = Calendar.getInstance();
        long millis = cal.getTimeInMillis();
        mEditor.putLong(buttonClickedKey,millis);
        mEditor.putBoolean(firstTimeUsedKey,false); //the button is clicked first time, so set the boolean to false.
        mEditor.commit();

        //hide the button after clicked
        BtnAdds.setVisibility(View.GONE);
    }

    private void checkPrefs(){
        if(firstTimeUsed==false){
            if(savedDate>0){
        //to 1, to be sure that the date differs only from day,month and year

        Calendar currentCal = Calendar.getInstance();
        currentCal.set(Calendar.MINUTE,1);
        currentCal.set(Calendar.HOUR,1);
        currentCal.set(Calendar.SECOND,1);
        currentCal.set(Calendar.MILLISECOND,1);

        Calendar savedCal = Calendar.getInstance();
        savedCal.setTimeInMillis(savedDate); //set the time in millis from saved in sharedPrefs
        savedCal.set(Calendar.MINUTE,1);
        savedCal.set(Calendar.HOUR,1);
        savedCal.set(Calendar.SECOND,1);
        savedCal.set(Calendar.MILLISECOND,1);

        if(currentCal.getTime().after(savedCal.getTime()))
        {
            BtnAdds.setVisibility(View.VISIBLE);
        } if(currentCal.getTime().equals(savedCal.getTime()))
                {
            BtnAdds.setVisibility(View.GONE);
        }

            }

               } else{
                  //just set the button visible if app is used the first time
                  BtnAdds.setVisibility(View.VISIBLE);
                 }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(String.valueOf(numSteps));
    }

    //codes for what happens when each cardview is clicked on in this case a new activity will be launched
    //adding on more codes later to pass data via sqlite or to other activity
    @Override
    public void onClick(View view) {


        Intent i;

        switch (view.getId()) {
            case R.id.test_cardview:
                i = new Intent(this, test.class);
                startActivity(i);
                break;

            case R.id.checklist_cardview:
                i = new Intent(this, checklist.class);
                startActivity(i);
                break;

            case R.id.totalsteps_cardview:
                i = new Intent(this, totalsteps.class);
                startActivity(i);
                break;

            case R.id.map_cardview:
                i = new Intent(this, PermissionsActivity.class);
                startActivity(i);
                break;
        }

        }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
