package com.example.alberto.smart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SensorActivity extends AppCompatActivity {

    private ListView LvSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ConfigDB configdb  = new ConfigDB(getApplicationContext());
        Config buscaconfig = configdb.busca();
        String ip = (buscaconfig.ip);

        LvSensor = (ListView) findViewById(R.id.lvSensors);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:"+ip+"/api/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SensorService service = retrofit.create(SensorService.class);

        Call<List<Sensor>> sensors = service.getSensor();

        sensors.enqueue(new Callback<List<Sensor>>() {
            @Override
            public void onResponse(Call<List<Sensor>> call, Response<List<Sensor>> response) {
                if(response.isSuccessful()){

                    List<Sensor> listaSensor = response.body();

                    SensorAdapter adapter = new SensorAdapter(getApplicationContext(),listaSensor);
                    LvSensor.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Call<List<Sensor>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro", Toast.LENGTH_LONG).show();

            }
        });


    }








}
