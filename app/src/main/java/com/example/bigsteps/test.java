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
public class test extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_INT = "com.example.bigsteps.extraint"; //this is a public constant cannot be changed

    // the id for this cardview are test_1, test_2, test_3, test_4 respectively.
    public CardView tests_1;
    public CardView tests_2;
    public CardView tests_3;
    public CardView tests_4;
    public CardView tests_5;
    public CardView tests_6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        //defining all cardview
        tests_1 = (CardView) findViewById(R.id.test_1);
        tests_2 = (CardView) findViewById(R.id.test_2);
        tests_3 = (CardView) findViewById(R.id.test_3);
        tests_4 = (CardView) findViewById(R.id.test_4);
        tests_5 = (CardView) findViewById(R.id.test_5);
        tests_6 = (CardView) findViewById(R.id.test_6);

        //assign onclick listener to my cards
        tests_1.setOnClickListener(this);
        tests_2.setOnClickListener(this);
        tests_3.setOnClickListener(this);
        tests_4.setOnClickListener(this);
        tests_5.setOnClickListener(this);
        tests_6.setOnClickListener(this);

    }

    // for diff card click what happen
    @Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId()) {
            case R.id.test_1:
                Integer checker_act = 1; // pass this value so that will determine which time on the timer will be set for testing
                i = new Intent(this, test_10_min.class);
                i.putExtra(EXTRA_INT, checker_act);
                startActivity(i);
                break;

            case R.id.test_2:
                Integer checker_act2 = 2; // pass this value so that will determine which time on the timer will be set for testing
                i = new Intent(this, test_10_min.class);
                i.putExtra(EXTRA_INT, checker_act2);
                startActivity(i);
                break;

            case R.id.test_3:
                Integer checker_act3 = 3; // pass this value so that will determine which time on the timer will be set for testing
                i = new Intent(this, test_10_min.class);
                i.putExtra(EXTRA_INT, checker_act3);
                startActivity(i);
                break;

            case R.id.test_4:
                Integer checker_act4 = 4; // pass this value so that will determine which time on the timer will be set for testing
                i = new Intent(this, test_10_min.class);
                i.putExtra(EXTRA_INT, checker_act4);
                startActivity(i);
                break;

            case R.id.test_5:
                Integer checker_act5 = 5; // pass this value so that will determine which time on the timer will be set for testing
                i = new Intent(this, test_10_min.class);
                i.putExtra(EXTRA_INT, checker_act5);
                startActivity(i);
                break;

            case R.id.test_6:
                Integer checker_act6 = 6; // pass this value so that will determine which time on the timer will be set for testing
                i = new Intent(this, test_10_min.class);
                i.putExtra(EXTRA_INT, checker_act6);
                startActivity(i);
                break;
        }

    }

}


