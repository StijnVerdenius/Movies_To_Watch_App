package com.example.user.stijnverdenius_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
//import Shared


public class MainActivity extends AppCompatActivity {


    TextView trys;
    SharedPreferences sharedPref;
    ArrayList<String> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("list1", MODE_PRIVATE);
        trys = (TextView) findViewById(R.id.trys);
        results = new ArrayList<String>();

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("resumed", "back");

//        SharedPreferences sharedPref2 = getSharedPreferences("list2", MODE_PRIVATE);

        int listsize;
        try {
            listsize = sharedPref.getAll().size();
        } catch (Exception e) {
            listsize = 0;
        }
//        String keyString2 = String.format("2listItem%d", listsize);

        String keyString1;
        for (int i = 0; i < listsize; i++ ) {
            keyString1 = String.format("1listItem%d", i);
            results.add(sharedPref.getString(keyString1, "leeg"));
        }
        makeAdapter(results);

//        Set<String> highScore2;

//        Set<String> s = Collections.emptySet();
//        highScore2 = sharedPref2.getStringSet(keyString2, s);

//        Log.d(keyString1, highScore);
//        Log.d(keyString2, highScore2.toString());
        trys.setText(results.toString());
    }

    public void pressAdd(View view) {
        Log.d("show", "button pressed");
        Intent inteNext = new Intent(this, ShowActivity.class);
        startActivity(inteNext);
//        String highScore = sharedPref.getString("uno", "leeg"); // getting String;
//        Log.d("banana", highScore);
//        trys.setText(highScore);
    }

    public void pressRemove(View view) {
        Log.d("remove", "button pressed");
        Intent inteNext = new Intent(this, AddActivity.class);
        startActivity(inteNext);
    }

    public void makeAdapter(ArrayList<String> results) {
        
    }





}
