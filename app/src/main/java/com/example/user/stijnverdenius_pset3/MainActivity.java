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
import java.util.Collections;
import java.util.Set;
//import Shared


public class MainActivity extends AppCompatActivity {


    TextView trys;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        trys = (TextView) findViewById(R.id.trys);

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("resumed", "back");
        SharedPreferences sharedPref = getSharedPreferences("list1", MODE_PRIVATE);
        SharedPreferences sharedPref2 = getSharedPreferences("list2", MODE_APPEND);

        int listsize;
        try {
            listsize = sharedPref.getAll().size();
        } catch (Exception e) {
            listsize = 0;
        }
        String keyString2 = String.format("2listItem%d", listsize+1);
        String keyString1 = String.format("1listItem%d", listsize+1);
        String highScore = sharedPref.getString(keyString1, "leeg"); // getting String;
        Set<String> highScore2;
//        try {
        Set<String> s = Collections.emptySet();
        highScore2 = sharedPref2.getStringSet(keyString2, s);
//        } catch (Exception e) {
//            Log.d("fail", "set");
//            highScore2 = Collections.emptySet();
//        }


        Log.d("ed1", highScore);
        Log.d("ed2", highScore2.toString());
        trys.setText(keyString1);
    }

    public void pressAdd(View view) {
        Log.d("show", "button pressed");
//        Intent inteNext = new Intent(this, ShowActivity.class);
//        startActivity(inteNext);
        String highScore = sharedPref.getString("uno", "leeg"); // getting String;
        Log.d("banana", highScore);
        trys.setText(highScore);
    }

    public void pressRemove(View view) {
        Log.d("remove", "button pressed");
        Intent inteNext = new Intent(this, AddActivity.class);
        startActivity(inteNext);
    }





}
