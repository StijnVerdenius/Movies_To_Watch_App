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
import java.util.Map;
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

    public void refresh() {
        results.clear();

        listMain.setAdapter(null);
        Map<String, ?> all = sharedPref.getAll();
        int listsize;
        try {
            listsize = all.size();
        } catch (Exception e) {
            listsize = -1;
        }

        String keyString1;
//        for (int i = 0; i <= listsize; i++ ) {
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            keyString1 = entry.getKey().toString();
            Log.d("map values",entry.getKey() + ": " +
                    entry.getValue().toString());

//                    String.format("1listItem%d", i);
            String tempstring = sharedPref.getString(keyString1, "");
            if (tempstring.equals("") == false) {
                results.add(tempstring);
            }
        }
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

//                TextView textView = (TextView) view.findViewById(R.id.list_content);
//                String text = textView.getText().toString();


//                String movie = getViewByPosition(position, listMain).getText();
                String movie = (String)(listMain.getItemAtPosition(position));
                Log.d("click", movie);
                toShow(movie);

            }
        });
        listMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String keyString3 = (String)(listMain.getItemAtPosition(position));
                getSharedPreferences("list1", MODE_PRIVATE).edit().remove(keyString3).commit();
                listMain.setAdapter(null);
//                listMain.setEmptyView(listMain);
                refresh();
                return true;
            }
        });
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    public void toShow(String position) {
        Intent inteNext = new Intent(this, ShowActivity.class);
//        String keyString2 = String.format("1listItem%d", position);
        inteNext.putExtra("movie", position);//sharedPref.getString(keyString2, ""));
        Log.d("startintent", position);
        startActivity(inteNext);
    }

}
