package com.example.alberto.smart;

/**
 * Created by Alberto on 20/06/2018.
 */

import android.os.AsyncTask;
import android.widget.Toast;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by Alberto on 26/11/2017.
 */

public class EnviaComando extends AsyncTask<String, Void, Integer> {


    @Override
    protected Integer doInBackground(String... params) {

        int status = 0;


        String ws = params[0];
        String envio = params[1];
        //String port_status = params[2];
        //String id = params[3];


        try {
            URL url = new URL(ws);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                urlConnection.setDoOutput(true);
                urlConnection.setChunkedStreamingMode(0);
                urlConnection.setRequestMethod("POST");

                DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());


                //String envio = "port="+port+"&port_status="+port_status+"&id="+id;
                //String envio = port;


                out.write(envio.getBytes(StandardCharsets.UTF_8));

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                if (in != null) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    status = Integer.parseInt(br.readLine());
                }
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return status;
    }




}