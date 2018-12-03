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

public class IluminacaoActivity extends AppCompatActivity {

    private ListView lvLights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iluminacao);
        ConfigDB configdb  = new ConfigDB(getApplicationContext());
        Config buscaconfig = configdb.busca();
        String ip = (buscaconfig.ip);


        lvLights = (ListView) findViewById(R.id.lvLights);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:"+ip+"/api/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LightService service = retrofit.create(LightService.class);

        Call<List<Light>> lights = service.getLight();

        lights.enqueue(new Callback<List<Light>>() {
            @Override
            public void onResponse(Call<List<Light>> call, Response<List<Light>> response) {
                if(response.isSuccessful()){

                    List<Light> listaLight = response.body();

                    LightsAdapter adapter = new LightsAdapter(getApplicationContext(),listaLight);
                    lvLights.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Call<List<Light>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Erro", Toast.LENGTH_LONG).show();

            }
        });

    }
}
