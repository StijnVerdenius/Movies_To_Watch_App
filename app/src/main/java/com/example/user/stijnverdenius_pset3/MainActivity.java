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
        sharedPref = getSharedPreferences("your_file_name", MODE_PRIVATE);
        String highScore = sharedPref.getString("uno", "leeg"); // getting String;
        Log.d("banana", highScore);
        trys.setText(highScore);
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
