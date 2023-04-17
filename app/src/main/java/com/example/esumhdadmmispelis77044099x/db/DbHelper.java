package com.example.esumhdadmmispelis77044099x.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "BDPelis.db";
    public static final String TABLA_USUARIOS = "t_usuarios";
    public static final String TABLA_PELICULAS = "t_peliculas";
    public static final String TABLA_PLATAFORMAS = "t_plataformas";

    //CAMPOS DE TABLA PELICULAS

    public static final String caratula = "caratula";
    public static final String titulo = "titulo";
    public static final String genero = "genero";
    public static final String calificacion = "calificacion";
    public static final String duracion = "duracion";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //TODO: Me falta por averiguar como hacer la movida de las imagenes = en peliculas:"caratula", en plataformas:"imagenes" y concretar los atributos a las especificaciones de la práctica

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_USUARIOS + "( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT NOT NULL, " +
                "password TEXT NOT NULL)");

       sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_PELICULAS + "( " +
                "id_pelis INTEGER PRIMARY KEY AUTOINCREMENT," +
               /*  "id_usuario REFERENCES TABLA_USUARIOS(id), " +
                "id_platadorma REFERENCES TABLA_PLATAFORMAS(id), " +*/
                caratula + " BLOB NOT NULL, " +
                titulo + " TEXT NOT NULL, " +
                genero + " TEXT NOT NULL, " +
                calificacion + " TEXT NOT NULL, " +
                duracion + " TEXT NOT NULL)");

        /*sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_PLATAFORMAS + "( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_usuario REFERENCES TABLA_USUARIOS(id), " +
                "nombre_plataforma TEXT NOT NULL, " +
                "url_acceso_plataforma TEXT NOT NULL, " +
                "nombre_usuario TEXT NOT NULL, " +
                "password TEXT NOT NULL)");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLA_USUARIOS);
       sqLiteDatabase.execSQL("DROP TABLE " + TABLA_PELICULAS);
        /*  sqLiteDatabase.execSQL("DROP TABLE " + TABLA_PLATAFORMAS);*/
        onCreate(sqLiteDatabase);
    }
    //TODO: como hacer el upgrade del resto de tablas



    //Metodo para checkear si existe el usuario introducido en el login
    public Cursor consultarUsuPas(String usu, String pas) throws SQLException {
        Cursor mcursor = null;
        mcursor = this.getReadableDatabase().query(TABLA_USUARIOS, new String[]{"id", "email", "password"}, "email like '"+ usu +"' and password like '"+ pas +"'", null, null, null, null);
        return mcursor;
    }
}
