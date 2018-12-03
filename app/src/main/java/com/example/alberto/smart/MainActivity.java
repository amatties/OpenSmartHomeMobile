package com.example.alberto.smart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    private EditText user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.edtEmail);
        pass = (EditText) findViewById(R.id.edtSenha);


    }
    public void login(View v){
        if(!user.getText().toString().equals("") && !pass.getText().toString().equals("")) {
           final RequestQueue request = Volley.newRequestQueue(MainActivity.this);
            ConfigDB configdb  = new ConfigDB(getApplicationContext());
            Config buscaconfig = configdb.busca();
            String ip = (buscaconfig.ip);
            String url = "http://"+ip+"/api/login/";

            StringRequest stringrequest = new StringRequest(StringRequest.Method.GET, url+ user.getText().toString() + "/" + pass.getText().toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        int value_response = Integer.parseInt(response);
                        //Toast.makeText(MainActivity.this,String.valueOf(value_response),Toast.LENGTH_SHORT).show();

                        if (value_response == 1) {

                            startActivity(new Intent(MainActivity.this, BaseActivity.class));
                            Toast.makeText(MainActivity.this,"Logado!",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Login inválido!", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "Sem conexão!", Toast.LENGTH_LONG).show();
                }
            });
            request.add(stringrequest);
        }else{
            Toast.makeText(MainActivity.this,"Login inválido!",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        MainActivity.this.finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuConfig) {
            Intent config = new Intent(this, ConfigActivity.class);
            startActivity(config);
        }


        return super.onOptionsItemSelected(item);
    }

}