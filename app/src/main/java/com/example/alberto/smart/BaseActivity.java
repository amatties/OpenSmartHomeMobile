package com.example.alberto.smart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuIlumina) {
            Intent iluminacao = new Intent(this, IluminacaoActivity.class);
            startActivity(iluminacao);
        }
        if (item.getItemId() == R.id.menuTranca) {
            Intent tranca = new Intent(this, LockActivity.class);
            startActivity(tranca);
        }
        if (item.getItemId() == R.id.menuDispositivo) {
            Intent dispositivo = new Intent(this, DeviceActivity.class);
            startActivity(dispositivo);
        }
        if (item.getItemId() == R.id.menuSensor) {
            Intent sensor = new Intent(this, SensorActivity.class);
            startActivity(sensor);
        }
        if (item.getItemId() == R.id.menuSair) {
            this.finish();

        }

        return super.onOptionsItemSelected(item);
    }

}

