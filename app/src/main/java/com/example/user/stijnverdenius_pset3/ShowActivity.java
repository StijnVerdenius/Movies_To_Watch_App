package com.example.user.stijnverdenius_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.R.attr.width;


public class ShowActivity extends AppCompatActivity {

    JSONObject trackstreamObj;
    TextView descript;
    TextView actors;
    TextView title;
    TextView Year;
    ImageView picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent intentArriving = getIntent();
        String Input = intentArriving.getStringExtra("movie");
        trackSearch(Input);
        descript = (TextView) findViewById(R.id.discription);
        actors = (TextView) findViewById(R.id.actors);
        Year = (TextView) findViewById(R.id.year);
        title = (TextView) findViewById(R.id.title1);
        picture = (ImageView) findViewById(R.id.piccu);

    }

    public void trackSearch(String Input) {
        Async2 asyncTask2 = new Async2(this);
        asyncTask2.execute(Input);
    }

    /*
    look for movie and show details
    */
    public void trackStartIntent(String dataRaw) {

        try {
            trackstreamObj = new JSONObject(dataRaw);
            title.setText ((String) trackstreamObj.get("Title"));
            Year.setText((String) trackstreamObj.get("Year"));
            descript.setText((String) trackstreamObj.get("Plot"));
            actors.setText((String) trackstreamObj.get("Actors"));


            try {
                URL url = new URL((String) trackstreamObj.get("Poster") );
                Log.d("url", url.toString());

                DisplayMetrics displaymetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                int width = (9*displaymetrics.heightPixels) /32;
                Picasso.with(this)
                        .load(String.valueOf(trackstreamObj.get("Poster"))).resize(width,width).into(picture);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }




    }


}


