package com.example.user.stijnverdenius_pset3;

/**
 * Created by User on 2/24/2017.
 */

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

import android.util.Log;

public class get_hppt_reqt {

    protected static synchronized String downloadFromServer(String... params) {
        String result = "";
        String chosentag = params[0];

        chosentag.replaceAll(" ", "_");

        String urlstring = String.format("http://www.omdbapi.com/?t=%s",chosentag);

        URL url = null;

        try {
            url = new URL(urlstring);
        } catch (MalformedURLException e) {
            Log.d("get_hppt exc", "url fout");
        }

        HttpURLConnection connect;

        if (url != null) {
            try {
                connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("GET");

                Integer responseCode = connect.getResponseCode();
                if (responseCode >= 200 && responseCode < 300) {
                    BufferedReader breader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                    String line;
                    while ((line = breader.readLine()) != null) {
                        result += line;
                    }
                }

            } catch (IOException e) {
                Log.d("get_hppt exc", "url != null fout");

            }
        }


        return result;


    }


}