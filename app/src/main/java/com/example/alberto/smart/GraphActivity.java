package com.example.alberto.smart;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;


import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONObject;



import java.text.SimpleDateFormat;


import java.util.Date;



public class GraphActivity extends AppCompatActivity {


    private GraphView graphView;
    private JSONArray array;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Intent it = getIntent();
        String type = it.getStringExtra("type");
        ConfigDB configdb  = new ConfigDB(getApplicationContext());
        Config buscaconfig = configdb.busca();
        String ip = (buscaconfig.ip);

        String url_graph = "http://"+ip+"/api/sensor/";




        final RequestQueue request = Volley.newRequestQueue(GraphActivity.this);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url_graph+type, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            graphView = (GraphView) findViewById(R.id.graph);
                            final SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                            SimpleDateFormat format1 = new SimpleDateFormat("hh:mm:ss");


                            JSONArray array = response.getJSONArray("dados");
                            String titulo = response.getString("titulo")+" - Leituras: "+array.length()+" - Data: "+response.getString("data");
                            int length = array.length();
                            if(length<=0){
                                Toast.makeText(getApplicationContext(),"Sem dados na data solicitada",Toast.LENGTH_LONG).show();
                                return;
                            }
                            final long dates[] = new long[array.length()];
                            DataPoint pontos[] = new DataPoint[array.length()];
                            for(int i=0;i<array.length();i++){
                                JSONObject objeto = new JSONObject(array.getString(i));
                                String string = objeto.getString("created_at");
                                String[] parts = string.split(" ");
                                Date d1 = format1.parse(parts[1]);
                                dates[i] = d1.getTime();
                                pontos[i] = new DataPoint(i,objeto.getInt("data"));
                            }

                            final LineGraphSeries<DataPoint> graph = new LineGraphSeries<DataPoint>(pontos);
                            graphView.getViewport().setMinX(1);
                            graphView.getViewport().setMaxX(8);
                           // graphView.getViewport().setXAxisBoundsManual(true);
                            graphView.getViewport().setScrollable(true);
                            graphView.getViewport().setScalable(true);
                           // graphView.getGridLabelRenderer().setHumanRounding(false);

                            graphView.setBackgroundColor(Color.argb(50, 93, 106, 127));
                            Paint paint = new Paint();
                            paint.setStyle(Paint.Style.STROKE);
                            paint.setStrokeWidth(10);
                            paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
                            graph.setCustomPaint(paint);


                            graphView.setTitle(titulo);
                            graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
                                @Override
                                public String formatLabel(double value, boolean isValueX) {

                                        if (isValueX) {
                                            int tt = Math.round((long) value);

                                            long date = dates[tt];


                                            //return super.formatLabel(value, isValueX);
                                            return format.format(new Date(date));


                                        } else {
                                            return super.formatLabel(value, isValueX);
                                        }


                                }
                            });
                            if(array.length()<8){
                                graphView.getGridLabelRenderer().setNumHorizontalLabels(array.length());
                            }else {
                                graphView.getGridLabelRenderer().setNumHorizontalLabels(8);
                            }
                            graphView.getGridLabelRenderer().setLabelVerticalWidth(100);
                            graph.setDrawDataPoints(true);
                           // graphView.getGridLabelRenderer().setHorizontalLabelsAngle(135);


                            graphView.addSeries(graph);


                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        request.add(jsonObjectRequest );
    }



}

