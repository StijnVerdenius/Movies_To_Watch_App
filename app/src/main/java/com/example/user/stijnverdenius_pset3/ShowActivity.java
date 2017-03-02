package com.example.user.stijnverdenius_pset3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.KeyEvent;


public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

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



}


