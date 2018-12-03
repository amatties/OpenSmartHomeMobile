package com.example.alberto.smart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText edtIp;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

      edtIp = (EditText) findViewById(R.id.edtIp);
      btnSalvar = (Button) findViewById(R.id.btnSalvar);

        InputFilter[] filters = new InputFilter[1];
        ConfigDB configdb  = new ConfigDB(getApplicationContext());

        final Config buscaconfig = configdb.busca();
        edtIp.setText(buscaconfig.ip);

        btnSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ConfigDB configdb = new ConfigDB(getApplicationContext());

        configdb.insereConf(new Config((long) 0, edtIp.getText().toString()));
        Toast.makeText(getApplicationContext(),"Configuração salva!",Toast.LENGTH_LONG).show();
        this.finish();
    }
}
