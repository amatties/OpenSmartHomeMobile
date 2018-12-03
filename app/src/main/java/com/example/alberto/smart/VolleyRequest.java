package com.example.alberto.smart;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyRequest {

    public static String getResponse(final Context context, int METHOD, String url){
        final String[] resposta = {""};
        RequestQueue volleyrequest = Volley.newRequestQueue(context);

        StringRequest stringrequest = new StringRequest(METHOD, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                resposta[0] = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Verifique a conexão",Toast.LENGTH_LONG).show();
            }
        });
        volleyrequest.add(stringrequest);
        return resposta[0];
    }


}
