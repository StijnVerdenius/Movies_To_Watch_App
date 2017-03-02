package com.example.user.stijnverdenius_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.KeyEvent;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class ShowActivity extends AppCompatActivity {

    JSONObject trackstreamObj;
    String Input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intentArriving = getIntent();
        Input = intentArriving.getStringExtra("movie");
        trackSearch();
    }

    public void trackSearch() {
        Async2 asyncTask2 = new Async2(this);
        asyncTask2.execute(Input);
    }

    public void trackStartIntent(String dataRaw) {

        ArrayList<String> data = new ArrayList<String>();
        try {
            trackstreamObj = new JSONObject(dataRaw);
            String title = (String) trackstreamObj.get("Title");
            String year = (String) trackstreamObj.get("Year");
            String director = (String) trackstreamObj.get("Director");
            String plot = (String) trackstreamObj.get("Plot");
            String actors = (String) trackstreamObj.get("Actors");
            String image = (String) trackstreamObj.get("Poster");

            data.add(title);
            data.add(year);
            data.add(director);
            data.add(plot);
            data.add(actors);
            data.add(image);

        } catch (JSONException e) {
            e.printStackTrace();
        }



        if (data.size() == 0) {
            String tellthem = "Movie not found :(";
            Toast.makeText(this, tellthem, (Toast.LENGTH_SHORT)).show();
        }

        SharedPreferences sharedPref = getSharedPreferences("list1", MODE_APPEND);
        SharedPreferences sharedPref2 = getSharedPreferences("list2", MODE_APPEND);

        int listsize;
        try {
            listsize = sharedPref.getAll().size();
        } catch (Exception e) {
            listsize = 0;
        }

        String keyString1 = String.format("1listItem%d", listsize);
        String keyString2 = String.format("2listItem%d", listsize);

        SharedPreferences.Editor editor1 = sharedPref.edit();
        SharedPreferences.Editor editor2 = sharedPref2.edit();

        editor1.putString(keyString1, data.get(0));

        Set<String> set2 = new HashSet<String>();

        set2.addAll(data);
        editor2.putStringSet(keyString2, set2);

        editor1.commit();
        editor2.commit();

        Log.d(keyString1, data.get(0));
        Log.d(keyString2, set2.toString());

        finish();
    }


}


