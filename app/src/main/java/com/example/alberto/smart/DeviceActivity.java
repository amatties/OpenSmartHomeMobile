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

public class DeviceActivity extends AppCompatActivity {


    private ListView LvDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        LvDevices = (ListView) findViewById(R.id.lvDevices);
        ConfigDB configdb  = new ConfigDB(getApplicationContext());
        Config buscaconfig = configdb.busca();
        String ip = (buscaconfig.ip);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:"+ip+"/api/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DeviceService service = retrofit.create(DeviceService.class);

        Call<List<Device>> devices = service.getDevice();

        devices.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                if(response.isSuccessful()){

                    List<Device> listaDevice = response.body();

                    DeviceAdapter adapter = new DeviceAdapter(getApplicationContext(),listaDevice);
                    LvDevices.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro", Toast.LENGTH_LONG).show();

            }
        });


    }
}