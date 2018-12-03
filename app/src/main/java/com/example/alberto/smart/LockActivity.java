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

public class LockActivity extends AppCompatActivity {

   private ListView LvLocks;
    //private ListView lvLights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        ConfigDB configdb  = new ConfigDB(getApplicationContext());
        Config buscaconfig = configdb.busca();
        String ip = (buscaconfig.ip);


        LvLocks = (ListView) findViewById(R.id.lvLocks);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:"+ip+"/api/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LockService service = retrofit.create(LockService.class);

        Call<List<Lock>> locks = service.getLock();

        locks.enqueue(new Callback<List<Lock>>() {
            @Override
            public void onResponse(Call<List<Lock>> call, Response<List<Lock>> response) {
                if(response.isSuccessful()){

                    List<Lock> listaLock = response.body();

                    LocksAdapter adapter = new LocksAdapter(getApplicationContext(),listaLock);
                    LvLocks.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Call<List<Lock>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro", Toast.LENGTH_LONG).show();

            }
        });


    }
}
