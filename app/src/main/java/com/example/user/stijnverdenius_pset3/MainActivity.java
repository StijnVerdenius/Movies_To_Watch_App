package com.example.user.stijnverdenius_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Map;



public class MainActivity extends AppCompatActivity {


    // initiation
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

    /*
    refresh listview
    */
    public void refresh() {

        // clearing
        results.clear();
        listMain.setAdapter(null);

        // loop through preferences
        Map<String, ?> all = sharedPref.getAll();
        int listsize;
        try {
            listsize = all.size();
        } catch (Exception e) {
            listsize = -1;
        }

        // add entries
        String keyString1;
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            keyString1 = entry.getKey().toString();
            Log.d("map values",entry.getKey() + ": " + entry.getValue().toString());
            String tempstring = sharedPref.getString(keyString1, "");
            if (tempstring.equals("") == false) {
                results.add(tempstring);
            }
        }

        // make adapter
        if (listsize > 0) {
            makeAdapter(results);
        }
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
        listMain.setAdapter(null);
        Log.d("resumed", "back");
        refresh();

    }

    /*
     add new
    */
    public void pressAdd(View view) {
        Log.d("show", "button pressed");
        Intent inteNext = new Intent(this, AddActivity.class);
        startActivity(inteNext);
    }

    /*
     remove all
    */
    public void pressRemove(View view) {
        Log.d("remove", "button pressed");
        listMain.setAdapter(null);
        this.getSharedPreferences("list1", MODE_PRIVATE).edit().clear().commit();
        this.getSharedPreferences("list2", MODE_PRIVATE).edit().clear().commit();
    }


    /*
    regulates adapter
     */
    public void makeAdapter(ArrayList<String> results) {
        ListAdapter arrdap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results );
        listMain.setAdapter(arrdap);

        // on click look at item
        listMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("click", Integer.toString(position));
                String movie = (String)(listMain.getItemAtPosition(position));
                Log.d("click", movie);
                toShow(movie);

            }
        });

        // on long click remove item
        listMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String keyString3 = (String)(listMain.getItemAtPosition(position));
                getSharedPreferences("list1", MODE_PRIVATE).edit().remove(keyString3).commit();
                listMain.setAdapter(null);
                refresh();
                return true;
            }
        });
    }

    /*
    shows a movie
    */
    public void toShow(String position) {
        Intent inteNext = new Intent(this, ShowActivity.class);
        inteNext.putExtra("movie", position);
        Log.d("startintent", position);
        startActivity(inteNext);
    }

}
