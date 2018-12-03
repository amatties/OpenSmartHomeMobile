package com.example.alberto.smart;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SensorAdapter extends BaseAdapter implements View.OnClickListener {


    private Context ctx;
    private List<Sensor> sensors;

    public SensorAdapter(Context ctx, List<Sensor> sensors) {
        this.ctx = ctx;
        this.sensors = sensors;
    }

    @Override
    public int getCount() {
        return sensors.size();
    }

    @Override
    public Object getItem(int i) {
        return sensors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Sensor sensor = sensors.get(i);
        final View linha = LayoutInflater.from(ctx).inflate(R.layout.item_sensor, null);
       // TextView txtName = (TextView) linha.findViewById(R.id.txtName);
        //txtName.setText("Nome: "+sensor.getType());
        Button btnOpen = (Button) linha.findViewById(R.id.btn1);
        btnOpen.setText(sensor.getType());
        btnOpen.setOnClickListener(this);



            return linha;


    }

    @Override
    public void onClick(View view) {
        Intent it = new Intent(ctx.getApplicationContext(), GraphActivity.class);
        Button b = (Button)view.findViewById(R.id.btn1);
        String buttonText = b.getText().toString();
       // Toast.makeText(ctx.getApplicationContext(),buttonText,Toast.LENGTH_LONG).show();


        it.putExtra("type",buttonText);
        ctx.startActivity(it);

    }


}
