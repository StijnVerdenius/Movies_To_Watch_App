package com.example.user.stijnverdenius_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 2/24/2017.
 */

public class TrackAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    AddActivity mainact;

    public TrackAsyncTask(AddActivity main) {
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

        String title;
        try {
            JSONObject trackstreamObj = new JSONObject(result);
            title = (String) trackstreamObj.get("Title");

        } catch (JSONException e) {
            e.printStackTrace();
            title = "";
        }
        Log.d("recieved : ", title);
        this.mainact.trackStartIntent(title);
    }


}
