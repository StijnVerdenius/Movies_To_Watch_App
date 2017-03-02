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
        String tellthem = "searching...";
        Toast.makeText(context, tellthem, (Toast.LENGTH_SHORT)).show();
    }

    @Override
    protected String doInBackground(String... params) {
        return get_hppt_reqt.downloadFromServer(params);
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        ArrayList<String> data = new ArrayList<String>();
        try {
            JSONObject trackstreamObj = new JSONObject(result);
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
        Log.d("data", data.toString());
        this.mainact.trackStartIntent(data);

    }


}

