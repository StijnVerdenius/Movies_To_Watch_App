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

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            Log.d("back", "ANDROID BUTTON PRESSED");
//            Intent inteNext = new Intent(this, MainActivity.class);
//            startActivity(inteNext);
//            finish();
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }

    public void pressAdd(View view) {
        trackSearch(view);
    }

    public void trackStartIntent(ArrayList<String> data) {

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
