package com.example.alberto.smart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LocksAdapter extends BaseAdapter implements View.OnClickListener {

    private Context ctx;
    private List<Lock> locks;
    int id;


    public LocksAdapter(Context ctx, List<Lock> locks) {
        this.ctx = ctx;
        this.locks = locks;
    }

    @Override
    public int getCount() {
        return locks.size();
    }

    @Override
    public Object getItem(int i) {
        return locks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, final View view, ViewGroup viewGroup) {
        final Lock lock = locks.get(i);

        final View linha = LayoutInflater.from(ctx).inflate(R.layout.item_lock, null);
        TextView txtName = (TextView) linha.findViewById(R.id.txtName);




        Button btnOpen = (Button) linha.findViewById(R.id.btn1);
        btnOpen.setOnClickListener(this);
        btnOpen.setTag(i);


        txtName.setText("Nome: "+lock.getName());

        return linha;


    }

    @Override
    public void onClick(View view) {
        EnviaComando enviaComando = new EnviaComando();
        ConfigDB configdb  = new ConfigDB(ctx);
        Config buscaconfig = configdb.busca();
        String ip = (buscaconfig.ip);

        int position = (Integer) view.getTag();
       Lock lock =  locks.get(position);
       int id = lock.getId();

        String saida = "id="+id;

       Toast.makeText(ctx,"Tranca aberta!",Toast.LENGTH_LONG).show();
        enviaComando.execute("http://"+ip+"/api/open",saida);

    }
}
