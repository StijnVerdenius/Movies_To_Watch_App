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

public class AddActivity extends AppCompatActivity {

    EditText Input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Input = (EditText) findViewById(R.id.InputText);
        assert Input != null;
//        Bundle extras =


    }


    public void trackSearch(View view) {
        String title = Input.getText().toString();
        TrackAsyncTask asyncTask = new TrackAsyncTask(this);
        asyncTask.execute(title);
        Input.getText().clear();
    }



    public void pressAdd(View view) {
        trackSearch(view);
    }

    public void trackStartIntent(ArrayList<String> data) {

        if (data.size() == 0) {
            String tellthem = "Movie not found :(";
            Toast.makeText(this, tellthem, (Toast.LENGTH_SHORT)).show();
        }

//        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
//        Editor editor = pref.edit();


        SharedPreferences sharedPref = this.getPreferences(this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("uno", data.toString());
        editor.commit();
        finish();
    }
}
