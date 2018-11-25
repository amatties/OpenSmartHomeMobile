package com.example.alberto.smart;

/**
 * Created by Alberto on 20/06/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alberto on 25/11/2017.
 */

public class ConfigDB extends SQLiteOpenHelper {


    public ConfigDB(Context context) {
        super(context,"ligths.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists configs(" +
                "_id integer primary key autoincrement," +
                "outpin integer, status integer);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }


    public long insereConf(Config config){
        SQLiteDatabase db = getReadableDatabase();
        try{

            Cursor c = db.query("configs",null, "outpin=?", new String[]{String.valueOf(config.getOutpin())}, null,null,null);

            ContentValues dados = new ContentValues();
            dados.put("outpin",config.getOutpin());
            dados.put("status",config.getStatus());
            //dados.put("topic",config.getTopic());


            if(c.getCount()>0){
                // se existir altera os dados

                long id = db.update("configs", dados, "outpin=?", new String[]{String.valueOf(config.getOutpin())});
                return id = 12;


            }else{
                //insere caso não existir

                long id = db.insert("configs", "", dados);
                return id = 10;


            }

        }finally {
            db.close();
        }



    }

    public Config busca(long outpin) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.query("configs", null, "outpin=?", new String[]{String.valueOf(outpin)}, null, null, null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                int out = c.getInt(1);
                long id = c.getLong(0);
                int status = c.getInt(2);
                String top = c.getString(2);
                //Boolean status = Boolean.parseBoolean(c.getString(2));


                return new Config(id, out, status,top);

            } else {
                return new Config((long) 0, 0, 0,"");

            }

        } finally {
            db.close();
        }


    }}
