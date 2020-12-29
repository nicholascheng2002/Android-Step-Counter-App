package com.example.bigsteps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class test_10_min extends AppCompatActivity implements SensorEventListener, StepsListener {
   // private long mStartTimeInMillis; // keep value 600000

    //define widgets
    private TextView mTextViewCountDown;
    private TextView mstep_display;
    private Button mButtonReset;
    private Button mButtonStartPause;
    private Button mBtnChange;
    public int stepsNum;
    private TextView goal_textviews; //wdiget for the textview goal display

    private Cursor model2 = null; // for inserting data into sqlite
    private MyDatabaseHelper2 helper2 = null; // for inserting data into sqlite

    //public static boolean checkingnumber; //tell us if the we reach our steps

    public int datachecklist; // this is for checking which test was completed and open different test result on checklist activity
    public int checkingnumber; //this is for sending to sql wether if the user pass or fail the test
    public long teststep; //this is for checking the actual step with step goal

    //codes related to the home page step counter using accelerometer sensor
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;

    //we need countdowntimer
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning; // tell us if timer is running or not
    //this code below is not needed.
    //private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mStartTimeInMillis; // this determines the duration of the countdown timer
    private long mTimeLeftInMillis;
    private long mEndTime; // for making sure that when phone flips between landscape and portrait it dosent slow down the timer
    // public static final String TIME = "startTimeInMillis"; // storing the value for time in sharedprefs

    // private Long text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.min_10);

        //get an instance of the SensorManager for the home page
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        mTextViewCountDown = (TextView) findViewById(R.id.text_view_countdown);
        goal_textviews = (TextView) findViewById(R.id.goal_textview);
        mstep_display = (TextView) findViewById(R.id.step_display);
        mButtonStartPause = (Button) findViewById(R.id.button_start_pause);
        mButtonReset = (Button) findViewById(R.id.button_reset);
        mBtnChange = (Button) findViewById(R.id.btn_change);

        // for inserting data into sqlite database
        helper2 = new MyDatabaseHelper2(this);
        model2 = helper2.getCheck();

        datachecklist = 0; // reset the variable for checkking which result to open on the checklist
        checkingnumber = 3; // this will reset the checking number sent to sql database

        Intent i = getIntent();
        Integer number_test = i.getIntExtra(test.EXTRA_INT, 0);

        if(number_test == 1){
            goal_textviews.setText("Goal : 1000 steps");

            mStartTimeInMillis = 1000; // 10 mins 600000 ms

            teststep = 1; // variable to compare with actual steps

            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit(); // use editor to save our data

            editor.putLong("startTimeInMillis", mStartTimeInMillis);

            editor.apply();

            resetTimer();

             Toast.makeText(this,"10 min counter started", Toast.LENGTH_SHORT).show();
        } else if(number_test == 2) {
            goal_textviews.setText("Goal : 2000 steps");

            mStartTimeInMillis = 2000; // 20 mins 1200000ms

            teststep = 2; // variable to compare with actual steps

            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit(); // use editor to save our data

            editor.putLong("startTimeInMillis", mStartTimeInMillis);

            editor.apply();

            resetTimer();

            Toast.makeText(this,"20 min counter started", Toast.LENGTH_SHORT).show();
        } else if(number_test == 3){
            goal_textviews.setText("Goal : 3000 steps");

            teststep = 3; // variable to compare with actual steps

            mStartTimeInMillis = 3000; // 30 mins 1800000 ms

            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit(); // use editor to save our data

            editor.putLong("startTimeInMillis", mStartTimeInMillis);

            editor.apply();

            resetTimer();

            Toast.makeText(this,"30 min counter started", Toast.LENGTH_SHORT).show();
        } else if(number_test == 4){
            goal_textviews.setText("Goal : 4000 steps");

            teststep = 4; // variable to compare with actual steps

            mStartTimeInMillis = 4000; // 40 mins 2400000 ms

            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit(); // use editor to save our data

            editor.putLong("startTimeInMillis", mStartTimeInMillis);

            editor.apply();

            resetTimer();

            Toast.makeText(this,"40 min counter started", Toast.LENGTH_SHORT).show();
        } else if(number_test == 5){
            goal_textviews.setText("Goal : 5000 steps");

            teststep = 5; // variable to compare with actual steps

            mStartTimeInMillis = 5000; // 50 mins 3000000 ms

            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit(); // use editor to save our data

            editor.putLong("startTimeInMillis", mStartTimeInMillis);

            editor.apply();

            resetTimer();

            Toast.makeText(this,"50 min counter started", Toast.LENGTH_SHORT).show();

        } else if(number_test == 6){
            goal_textviews.setText("Goal : 6000 steps");

            teststep = 0; // variable to compare with actual steps

            mStartTimeInMillis = 6000; // 60 mins 3600000 ms

            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit(); // use editor to save our data

            editor.putLong("startTimeInMillis", mStartTimeInMillis);

            editor.apply();

            resetTimer();

            Toast.makeText(this,"60 min counter started", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"counter not started", Toast.LENGTH_SHORT).show();
        }


          // when first click on start button
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if our timer is running
                if (mTimerRunning) {
                    pauseTimer(); // if our timer is running we want to pause our timer
                    sensorManager.unregisterListener(test_10_min.this); // set the sensor to not work
                } else {
                    startTimer();// if our timer is not running we call startTimer to start timing
                    sensorManager.registerListener(test_10_min.this, accel, SensorManager.SENSOR_DELAY_FASTEST); //set the sensor to work
                }
            }
        });
        //when the reset button is click
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer(); // if our timer has finish counting and this button is clicked it will reset timer to original state before start
                sensorManager.unregisterListener(test_10_min.this); // set the sensor to not work
                stepsNum = 0;
                mstep_display.setText(String.valueOf(stepsNum));
            }
        });

        mBtnChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                teststep = 0; // this will reset the value for the steps for goal
                datachecklist = 0; // reset the variable for checkking which result to open on the checklist
                checkingnumber = 3; // this will reset the checking number sent to sql database

                mStartTimeInMillis = 0; // make the set time to 0 so can switch between diff challenge if dont want

                SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit(); // use editor to save our data

                editor.putLong("startTimeInMillis", mStartTimeInMillis);

                editor.apply();

                resetTimer();

                // change activity code back to test activity
                Intent i;
                i = new Intent(test_10_min.this, test.class);
                startActivity(i);
            }
        });

        mstep_display.setText(String.valueOf(stepsNum));

        // this is obsolete becos we already called it in our onStart
       // updateCountDownText(); // we call it because when we start we want to set textview from 00:00 to our actual time that is left

       // Log.d("lifecycle", "onCreate invoked");

    }

    /*private void setTime() {
        Intent i = getIntent();
        Integer number_test = i.getIntExtra(test.EXTRA_INT, 0);

        if(number_test == 1) {
             mStartTimeInMillis = 600000;
            resetTimer(mStartTimeInMillis);
        } else if (number_test == 2){
             mStartTimeInMillis = 1200000; // 20 mins
            resetTimer(mStartTimeInMillis);
        } else if (number_test == 3){
             mStartTimeInMillis = 1800000; // 30 mins
            resetTimer(mStartTimeInMillis);
        } else if (number_test == 4){
            mStartTimeInMillis = 2400000; // 40 mins
            resetTimer(mStartTimeInMillis);
        } else if (number_test == 5){
             mStartTimeInMillis = 3000000; // 50 mins
            resetTimer(mStartTimeInMillis);
        } else if (number_test == 6){
            mStartTimeInMillis = 3600000; // 60 mins
            resetTimer(mStartTimeInMillis);
        }
    } */

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis; // now we saved the time where our timer is supposed to end

        //pass our length and miliseconds
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) { // the value 1000 represents after how many miliseconds the onTick method will be called in this case it would be 1 second intervals
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText(); // this will update textview to show how many seconds
            }

            @Override
            public void onFinish() {
                mTimerRunning = false; // as soon as our countdowntimer finishes we set mtimerrunning to false becos its not running anymore
                mTextViewCountDown.setText("00:00");
                updateButtons();
                sensorManager.unregisterListener(test_10_min.this); // set the sensor to not work stop counter on finish

                Intent i = getIntent();
                Integer number_test = i.getIntExtra(test.EXTRA_INT, 0);

                if(number_test == 1){

                    // for checking which result to open on the checklist activity
                    datachecklist = 1; // this is for checking which result to open on the checklist activity

                    //on finish i want to check if the goal for the number of steps is reached
                if (teststep <= stepsNum){
                    //code here to change the boolean variable to true or false
                    checkingnumber = 0; // data to be sent to sql database and use to determine whether we will display

                    // for inserting data into the sqlite table for checking which result is displayed
                    helper2.insert2(datachecklist, checkingnumber);
                    model2 = helper2.getCheck();

                    Toast.makeText(test_10_min.this,"check ur result now on checklist Passed", Toast.LENGTH_SHORT).show();


                } else {
                    //code here to record down the value where the user did not manage to reach amount of steps in time
                    checkingnumber = 1;

                    // for inserting data into the sqlite table for checking which result is displayed
                    helper2.insert2(datachecklist, checkingnumber);
                    model2 = helper2.getCheck();

                    Toast.makeText(test_10_min.this,"check ur result now on checklist Failed", Toast.LENGTH_SHORT).show();

                }

                } else if (number_test == 2){

                    // for checking which result to open on the checklist activity
                    datachecklist = 2; // this is for checking which result to open on the checklist activity

                    //on finish i want to check if the goal for the number of steps is reached
                    if (teststep <= stepsNum){
                        //code here to change the boolean variable to true or false
                        checkingnumber = 0; // data to be sent to sql database and use to determine whether we will display

                        // for inserting data into the sqlite table for checking which result is displayed
                        helper2.insert2(datachecklist, checkingnumber);
                        model2 = helper2.getCheck();

                        Toast.makeText(test_10_min.this,"check ur result now on checklist Passed", Toast.LENGTH_SHORT).show();

                    } else {
                        //code here to record down the value where the user did not manage to reach amount of steps in time
                        checkingnumber = 1;

                        // for inserting data into the sqlite table for checking which result is displayed
                        helper2.insert2(datachecklist, checkingnumber);
                        model2 = helper2.getCheck();

                        Toast.makeText(test_10_min.this,"check ur result now on checklist Failed", Toast.LENGTH_SHORT).show();

                    }

                } else if (number_test == 3){

                    // for checking which result to open on the checklist activity
                    datachecklist = 3; // this is for checking which result to open on the checklist activity

                    //on finish i want to check if the goal for the number of steps is reached
                    if (teststep <= stepsNum){
                        //code here to change the boolean variable to true or false
                        checkingnumber = 0; // data to be sent to sql database and use to determine whether we will display

                        // for inserting data into the sqlite table for checking which result is displayed
                        helper2.insert2(datachecklist, checkingnumber);
                        model2 = helper2.getCheck();

                        Toast.makeText(test_10_min.this,"check ur result now on checklist Passed", Toast.LENGTH_SHORT).show();

                    } else {
                        //code here to record down the value where the user did not manage to reach amount of steps in time
                        checkingnumber = 1;

                        // for inserting data into the sqlite table for checking which result is displayed
                        helper2.insert2(datachecklist, checkingnumber);
                        model2 = helper2.getCheck();

                        Toast.makeText(test_10_min.this,"check ur result now on checklist Failed", Toast.LENGTH_SHORT).show();

                    }

                } else if (number_test == 4){

                    // for checking which result to open on the checklist activity
                    datachecklist = 4; // this is for checking which result to open on the checklist activity

                    //on finish i want to check if the goal for the number of steps is reached
                    if (teststep <= stepsNum){
                        //code here to change the boolean variable to true or false
                        checkingnumber = 0; // data to be sent to sql database and use to determine whether we will display

                        // for inserting data into the sqlite table for checking which result is displayed
                        helper2.insert2(datachecklist, checkingnumber);
                        model2 = helper2.getCheck();

                        Toast.makeText(test_10_min.this,"check ur result now on checklist Passed", Toast.LENGTH_SHORT).show();

                    } else {
                        //code here to record down the value where the user did not manage to reach amount of steps in time
                        checkingnumber = 1;

                        // for inserting data into the sqlite table for checking which result is displayed
                        helper2.insert2(datachecklist, checkingnumber);
                        model2 = helper2.getCheck();

                        Toast.makeText(test_10_min.this,"check ur result now on checklist Failed", Toast.LENGTH_SHORT).show();

                    }

                } else if (number_test == 5){

                    // for checking which result to open on the checklist activity
                    datachecklist = 5; // this is for checking which result to open on the checklist activity

                    //on finish i want to check if the goal for the number of steps is reached
                    if (teststep <= stepsNum){
                        //code here to change the boolean variable to true or false
                        checkingnumber = 0; // data to be sent to sql database and use to determine whether we will display

                        // for inserting data into the sqlite table for checking which result is displayed
                        helper2.insert2(datachecklist, checkingnumber);
                        model2 = helper2.getCheck();

                        Toast.makeText(test_10_min.this,"check ur result now on checklist Passed", Toast.LENGTH_SHORT).show();

                    } else {
                        //code here to record down the value where the user did not manage to reach amount of steps in time
                        checkingnumber = 1;

                        // for inserting data into the sqlite table for checking which result is displayed
                        helper2.insert2(datachecklist, checkingnumber);
                        model2 = helper2.getCheck();

                        Toast.makeText(test_10_min.this,"check ur result now on checklist Failed", Toast.LENGTH_SHORT).show();

                    }

                } else if (number_test == 6){

                    // for checking which result to open on the checklist activity
                    datachecklist = 6; // this is for checking which result to open on the checklist activity

                    //on finish i want to check if the goal for the number of steps is reached
                    if (teststep <= stepsNum){
                        //code here to change the boolean variable to true or false
                        checkingnumber = 0; // data to be sent to sql database and use to determine whether we will display

                        // for inserting data into the sqlite table for checking which result is displayed
                        helper2.insert2(datachecklist, checkingnumber);
                        model2 = helper2.getCheck();

                        Toast.makeText(test_10_min.this,"check ur result now on checklist Passed", Toast.LENGTH_SHORT).show();

                    } else {
                        //code here to record down the value where the user did not manage to reach amount of steps in time
                        checkingnumber = 1;

                        // for inserting data into the sqlite table for checking which result is displayed
                        helper2.insert2(datachecklist, checkingnumber);
                        model2 = helper2.getCheck();

                        Toast.makeText(test_10_min.this,"check ur result now on checklist Failed", Toast.LENGTH_SHORT).show();

                    }


                }

                //Toast.makeText(test_10_min.this,"check ur result now on checklist", Toast.LENGTH_SHORT).show();

                //on finish i want to check if the goal for the number of steps is reached
               /* if (teststep == stepsNum){
                    //code here to change the boolean variable to true or false
                    checkingnumber = 0; // data to be sent to sql database and use to determine whether we will display
                } else {
                    //code here to record down the value where the user did not manage to reach amount of steps in time
                    checkingnumber = 1;
                } */


               // mButtonStartPause.setText("Start");
              //  mButtonStartPause.setVisibility(View.INVISIBLE); // set it to invisible becos we first have to reset our timer becos we cant start it when its at zero
              //  mButtonReset.setVisibility(View.VISIBLE); // set reset to visible so can hit reset button and bring timer back to default value
            }
        }.start(); //as soon as we click our button we will immediately create this timer and immediately start it

        mTimerRunning = true;
        updateButtons();
       // mButtonStartPause.setText("pause"); // because when the timer is running we want to change the textview text to pause
       // mButtonReset.setVisibility(View.INVISIBLE); //set reset button to invisible because when we pause our timer we will make this button visible and resume our timer we wil have to set it to invisible

    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateButtons();
       // mButtonStartPause.setText("Start"); //change text to start when paused
       // mButtonReset.setVisibility(View.VISIBLE); // set the reset button visible as soon as timer is paused
    }

    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis ; // set it back to it's default timer
        updateCountDownText(); // we want to reset the text as well
        updateButtons();
      //  mButtonReset.setVisibility(View.INVISIBLE); // after reset we want to set it to visible
      //  mButtonStartPause.setVisibility(View.VISIBLE); // make button start visible after timer reach zero and reset is pressed
    }

    private void updateCountDownText(){
        // have to change our mTimeMillis into minutes and seconds to display to the textview ui for user to see
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds); // format this value into a string that looks like a clock
        mTextViewCountDown.setText(timeLeftFormatted); // this will update our countdown text
    }

    // to tidy up the buttons so that they are not everywhere in the code this function will be called instead to check button status
    private void updateButtons() {
        if (mTimerRunning) {
            mButtonReset.setVisibility(View.INVISIBLE); // if timer is running we want our reset button to be invisible
            mButtonStartPause.setText("Pause");  // if timer running our pause button be visible
        } else {
            mButtonStartPause.setText("Start"); // there is three scenarios the common thing in all 3 is that button start must be there
            if (mTimeLeftInMillis < 1000) {
               // mTextViewCountDown.setText("00:00"); // make ui view show 00:00
                mButtonStartPause.setVisibility(View.INVISIBLE);// if our timer is at 0 seconds we want our start/pause button to be invisible so we can reset it only
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE); // if it was not the case where time reach 0 seconds we want the button start / pause visible
            }
            if (mTimeLeftInMillis < mStartTimeInMillis) {
                mButtonReset.setVisibility(View.VISIBLE); // if our timer is lower than the starting time we want our reset button to be visible
            } else {
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }

    // this bunch of code onSaveInstanceState is obsolete
    /* // code to make ensure when configuration changes eg phone goes different landscape or potrait timer dosent fail
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("millisLeft", mTimeLeftInMillis);
        outState.putBoolean("timerRunning", mTimerRunning);
        outState.putLong("endTime", mEndTime); // for making the counter not become slower when phone is rotating
    } */

    // this bunch of code onRestoreInstanceState is obsolete
    /* // code to make ensure when configuration changes eg phone goes different landscape or potrait timer dosent fail
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimerRunning = savedInstanceState.getBoolean("timerRunning");
        updateCountDownText();
        updateButtons();

        if (mTimerRunning) {
            mEndTime = savedInstanceState.getLong("endTime"); // rotate phone
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis(); // rotate phone
            startTimer();
        }
    } */

    // codes to ensure that the timer is running even after the app is closed
    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit(); // use editor to save our data

        // here is where we should save the variables to the sharedpreference to update the time thing

        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);

        editor.apply(); // call this to save the value above

        // because we are restarting it onStart
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }

       // Log.d("lifecycle", "onStop invoked");
    }

    // codes to ensure that the timer is running even after the app is closed
    @Override
    protected void onStart() {
        super.onStart();

       SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 6000);
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        mTimerRunning = prefs.getBoolean("timerRunning", false); // this values will be loaded when we start our app the first time

        updateCountDownText(); // we call it because when we start we want to set textview from 00:00 to our actual time that is left
        updateButtons();

        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
               updateCountDownText();
                updateButtons();
            } else {
                startTimer();
            }
        }

       // Log.d("lifecycle", "onStart invoked");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void step(long timeNs) {
        stepsNum++;
        mstep_display.setText(String.valueOf(stepsNum));
    }

    @Override
    protected void onDestroy() {
        helper2.close();
        super.onDestroy();
    }
}
