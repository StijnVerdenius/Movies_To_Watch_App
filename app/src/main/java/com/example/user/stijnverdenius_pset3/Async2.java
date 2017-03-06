package com.example.user.stijnverdenius_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 3/2/2017.
 */

public class Async2 extends AsyncTask<String, Integer, String> {



    Context context;
    ShowActivity mainact;

    public Async2(ShowActivity main) {
        this.mainact = main;
        this.context = this.mainact.getApplicationContext();

    }

    @Override
    protected void onPreExecute() {
        String tellthem = "loading...";
        Toast.makeText(context, tellthem, (Toast.LENGTH_SHORT)).show();
    }

    @Override
    protected String doInBackground(String... params) {
        return get_hppt_reqt.downloadFromServer(params);
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);

        Log.d("data film", result);
        this.mainact.trackStartIntent(result);

    }


}

