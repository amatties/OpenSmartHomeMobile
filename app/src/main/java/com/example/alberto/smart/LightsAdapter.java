package com.example.alberto.smart;

/**
 * Created by Alberto on 20/06/2018.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;


/**
 * Created by Alberto on 25/11/2017.
 */

public class LightsAdapter extends BaseAdapter {



    private Context ctx;
    private List<Light> lights;
    String saida;
    int status;


    public LightsAdapter(Context ctx, List<Light> lights) {
        this.ctx = ctx;
        this.lights = lights;
    }

    @Override
    public int getCount() {
        return lights.size();
    }

    @Override
    public Object getItem(int i) {
        return lights.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, final View view, ViewGroup viewGroup) {
        final Light light = lights.get(i);
        final View linha = LayoutInflater.from(ctx).inflate(R.layout.item_light, null);
        ConfigDB configdb  = new ConfigDB(ctx);
        Config buscaconfig = configdb.busca();
        final String ip = (buscaconfig.ip);
        TextView txtName = (TextView) linha.findViewById(R.id.txtName);
       // TextView txTopic = (TextView) linha.findViewById(R.id.txtTopic);
        TextView txtOut = (TextView)linha.findViewById(R.id.txtOut);
        //TextView txtStatus = (TextView) linha.findViewById(R.id.txtStatus);
        //SeekBar seekBar = (SeekBar) linha.findViewById(R.id.seekBar);
        Switch sw = (Switch) linha.findViewById(R.id.switch1);
        txtName.setText("Nome: " + light.getName());
        txtOut.setText("Porta Saida: " + light.getPort());
        int state = light.getPort_status();
        if(state==0) {
            sw.setChecked(true);
        }else{
            sw.setChecked(false);
        }
               sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                EnviaComando enviaComando = new EnviaComando();
                if(b){
                    status = 1;
                    String saida = "port="+String.valueOf(light.getPort())+"&port_status="+String.valueOf(status)+"&id="+String.valueOf(light.getId());
                    enviaComando.execute("http://"+ip+"/api/acommand",saida);
                }else{
                    status = 0;
                    String saida = "port="+String.valueOf(light.getPort())+"&port_status="+String.valueOf(status)+"&id="+String.valueOf(light.getId());
                    enviaComando.execute("http://"+ip+"/api/acommand",saida);
                }
            }
        });

        return linha;
    }
}
