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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
//import Shared


public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    ArrayList<String> results;
    ListView listMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("list1", MODE_PRIVATE);
        results = new ArrayList<String>();
        listMain = (ListView) findViewById(R.id.listMain);

    }

    @Override
    public void onPause() {
        super.onPause();
        listMain.setAdapter(null);
        results.clear();
    }

    @Override
    public void onResume(){
        super.onResume();

        Log.d("resumed", "back");
        listMain.setAdapter(null);

        int listsize;
        try {
            listsize = sharedPref.getAll().size();
        } catch (Exception e) {
            listsize = -1;
        }

        String keyString1;
        for (int i = 0; i <= listsize; i++ ) {
            keyString1 = String.format("1listItem%d", i);
            results.add(sharedPref.getString(keyString1, ""));
        }
        if (listsize > 0) {
            makeAdapter(results);
        }

    }

    public void pressAdd(View view) {
        Log.d("show", "button pressed");
        Intent inteNext = new Intent(this, AddActivity.class);
        startActivity(inteNext);
    }

    public void pressRemove(View view) {
        Log.d("remove", "button pressed");
        listMain.setAdapter(null);
        this.getSharedPreferences("list1", MODE_PRIVATE).edit().clear().commit();
        this.getSharedPreferences("list2", MODE_PRIVATE).edit().clear().commit();
    }

    public void makeAdapter(ArrayList<String> results) {
        ListAdapter arrdap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results );
        listMain.setAdapter(arrdap);
        listMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("click", Integer.toString(position));
                toShow(position);

            }
        });
    }

    public void toShow(int position) {
        Intent inteNext = new Intent(this, ShowActivity.class);
        String keyString2 = String.format("1listItem%d", position);
        inteNext.putExtra("movie", sharedPref.getString(keyString2, ""));
        startActivity(inteNext);
    }






}
