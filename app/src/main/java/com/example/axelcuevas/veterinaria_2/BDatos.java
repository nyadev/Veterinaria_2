package com.example.axelcuevas.veterinaria_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Axel Cuevas on 04/04/2018.
 */

public class BDatos extends SQLiteOpenHelper {
    private static final String TABLA_ANIMALES = "create table animales (clave int primary key, nombre text)";

    public BDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(TABLA_ANIMALES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {

    }
}
