package com.example.esumhdadmmispelis77044099x.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.esumhdadmmispelis77044099x.Entities.Image;
import com.example.esumhdadmmispelis77044099x.Entities.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class DbPeliculas extends DbHelper{

    Context context;


    private Cursor cursor;
    private String[] campos = new String[] {caratula, titulo, genero, calificacion, duracion};

    public DbPeliculas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPeliculas(String titulo1, String genero1, String calificacion1, String duracion1, Image img) {
        long id = 0;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(caratula,img.getArrayFoto());
            values.put(titulo, titulo1);
            values.put(genero, genero1);
            values.put(calificacion, calificacion1);
            values.put(duracion, duracion1);

            id = db.insert(TABLA_PELICULAS, null, values);
        } catch (Exception ex){
            ex.toString();
        }


        return id;
    }


    /* Método para obtener todas las peliculas de la bd */
    public ArrayList<Image> obtenerImagenes()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Abrimos la base de datos 'bdpeliculas' en modo escritura
        db = getWritableDatabase();

        cursor = db.query(TABLA_PELICULAS, campos, "", null, null, null, null);

        ArrayList<Image> arrayImagenes = new ArrayList<Image>();
        Image peli;
        if (cursor.moveToFirst()) {
            do {
                peli = obtenerValores();

                arrayImagenes.add(peli);
            } while(cursor.moveToNext());
        }
        return arrayImagenes;
    }



    private Image obtenerValores(){

        Image img = new Image();
        img.setId(cursor.getInt(0));
        img.setArrayFoto(cursor.getBlob(1));

        return img;
    }

    public Cursor getPeliData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLA_PELICULAS, null );
        return cursor;
    }

    public List<Pelicula> getAllItems() {
        // Crear lista de items
        List<Pelicula> itemList = new ArrayList<>();

        // Obtener base de datos en modo lectura
        SQLiteDatabase db = this.getReadableDatabase();

        // Crear cursor para recorrer la tabla
        Cursor cursor = db.rawQuery("SELECT * FROM TABLA_PELICULAS", null);

        // Recorrer cursor y agregar items a la lista
        if (cursor.moveToFirst()) {
            do {
                int id_pelicula = cursor.getInt(0);
                byte[] caratula = cursor.getBlob(1);
                String titulo = cursor.getString(2);
                String genero = cursor.getString(3);
                String calificacion = cursor.getString(4);
                String duracion = cursor.getString(5);


                Pelicula item = new Pelicula(id_pelicula, caratula, titulo, genero, calificacion, duracion);
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        // Cerrar cursor y conexión
        cursor.close();
        db.close();

        // Retornar lista de items
        return itemList;
    }



}


