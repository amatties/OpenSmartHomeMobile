package com.example.alberto.smart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class DeviceAdapter extends BaseAdapter {


    private Context ctx;
    private List<Device> devices;
    int status;

    public DeviceAdapter(Context ctx, List<Device> devices) {
        this.ctx = ctx;
        this.devices = devices;
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Object getItem(int i) {
        return devices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Device device = devices.get(i);

        final View linha = LayoutInflater.from(ctx).inflate(R.layout.item_device, null);
        ConfigDB configdb  = new ConfigDB(ctx);
        Config buscaconfig = configdb.busca();
        final String ip = (buscaconfig.ip);


        TextView txtName = (TextView) linha.findViewById(R.id.txtName);
       // TextView txTopic = (TextView) linha.findViewById(R.id.txtTopic);
        TextView txtOut = (TextView)linha.findViewById(R.id.txtOut);
        TextView txtStatus = (TextView) linha.findViewById(R.id.txtStatus);
        //SeekBar seekBar = (SeekBar) linha.findViewById(R.id.seekBar);

        Switch sw = (Switch) linha.findViewById(R.id.switch1);


        txtName.setText("Nome: " + device.getName());
        txtOut.setText("Porta Saida: " + device.getPort());





        int state = device.getPort_status();

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

                    String saida = "port="+String.valueOf(device.getPort())+"&port_status="+String.valueOf(status)+"&id="+String.valueOf(device.getId());
                    enviaComando.execute("http://"+ip+"/api/acommand",saida);


                }else{

                    status = 0;
                    String saida = "port="+String.valueOf(device.getPort())+"&port_status="+String.valueOf(status)+"&id="+String.valueOf(device.getId());
                    enviaComando.execute("http://"+ip+"/api/acommand",saida);


                }

            }
        });


        return linha;
    }
}
