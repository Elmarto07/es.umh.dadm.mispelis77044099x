package com.example.esumhdadmmispelis77044099x.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbPlataformas extends DbHelper{

    Context context;

    public DbPlataformas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPlataformas(String nombre_plataforma, String url_acceso_plataforma, Integer nombre_usuario, Integer password) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("titulo", nombre_plataforma);
            values.put("genero", url_acceso_plataforma);
            values.put("calificacion", nombre_usuario);
            values.put("duracion", password);

            id = db.insert(TABLA_PLATAFORMAS, null, values);
        } catch (Exception ex){
            ex.toString();
        }


        return id;
    }
}
