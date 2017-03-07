package com.example.user.stijnverdenius_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AddActivity extends AppCompatActivity {

    EditText Input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Input = (EditText) findViewById(R.id.InputText);
        assert Input != null;
    }


    public void trackSearch(View view) {
        String title = Input.getText().toString();
        TrackAsyncTask asyncTask = new TrackAsyncTask(this);
        asyncTask.execute(title);
        Input.getText().clear();
    }

    // search for movie
    public void pressAdd(View view) {
        trackSearch(view);
    }

    /*
    recieve movie search and save in sharedpreferences
    */
    public void trackStartIntent(String data) {

        if (data.equals("")) {
            String tellthem = "Movie not found :(";
            Toast.makeText(this, tellthem, (Toast.LENGTH_SHORT)).show();
            Intent inteNext = new Intent(this, AddActivity.class);
            startActivity(inteNext);
            finish();
        }

        SharedPreferences sharedPref = getSharedPreferences("list1", MODE_APPEND);

        int listsize;
        try {
            listsize = sharedPref.getAll().size();
        } catch (Exception e) {
            listsize = 0;
        }

        String keyString1 = data;

        SharedPreferences.Editor editor1 = sharedPref.edit();

        editor1.putString(keyString1, data);

        editor1.commit();

        Log.d(keyString1, data);

        finish();
    }
}
