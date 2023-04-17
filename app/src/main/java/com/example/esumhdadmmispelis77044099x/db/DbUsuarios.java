package com.example.esumhdadmmispelis77044099x.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUsuarios extends DbHelper{

    Context context;

    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String email, String password) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("email", email);
            values.put("password", password);

            id = db.insert(TABLA_USUARIOS, null, values);
        } catch (Exception ex){
            ex.toString();
        }


        return id;
    }
}
