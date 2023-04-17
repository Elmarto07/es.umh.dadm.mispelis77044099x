package com.example.esumhdadmmispelis77044099x;

import static com.example.esumhdadmmispelis77044099x.db.DbHelper.TABLA_PELICULAS;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.esumhdadmmispelis77044099x.Adaptadores.Adaptador_Peliculas;
import com.example.esumhdadmmispelis77044099x.Entities.Image;
import com.example.esumhdadmmispelis77044099x.Entities.Pelicula;
import com.example.esumhdadmmispelis77044099x.db.DbHelper;
import com.example.esumhdadmmispelis77044099x.db.DbPeliculas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PantallaDeSeleccion extends AppCompatActivity {

    FloatingActionButton floatingAction;
    DbHelper DB;

    private RecyclerView mRecyclerView;
    private Adaptador_Peliculas mAdapterPe;



    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_de_seleccion);
        floatingAction = findViewById(R.id.floatingActionButton3);

        floatingAction.setOnClickListener((view) -> mostrarOpcionesAñadirPelisPlataformas(view));

        DB = new DbHelper(this);

        mRecyclerView = findViewById(R.id.recycler_peliculas);
        mAdapterPe = new Adaptador_Peliculas(getData());
        mRecyclerView.setAdapter(mAdapterPe);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public ArrayList<Pelicula> getData() {
        ArrayList<Pelicula> dataList = new ArrayList<>();
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor cursor = db.query(
                TABLA_PELICULAS,
                new String[]{"id_pelis", "caratula", "titulo", "genero", "calificacion", "duracion"},
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id_pelis"));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
            String genero = cursor.getString(cursor.getColumnIndexOrThrow("genero"));
            String calificacion = cursor.getString(cursor.getColumnIndexOrThrow("calificacion"));
            String duracion = cursor.getString(cursor.getColumnIndexOrThrow("duracion"));

            byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow("caratula"));
            Bitmap caratula = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

            Pelicula data = new Pelicula(id, imageBytes, titulo, genero, calificacion, duracion);
            dataList.add(data);
        }
        cursor.close();
        return dataList;
    }

    public void mostrarOpcionesAñadirPelisPlataformas(View v) {
        PopupMenu app = new PopupMenu(this, floatingAction);
        app.getMenuInflater().inflate(R.menu.menu_insertar_plataformas_peliculas, app.getMenu());
        app.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.PELICULAS_menuItem:
                        Toast.makeText(PantallaDeSeleccion.this, "hola", Toast.LENGTH_SHORT).show();
                        Intent ir_anyadir_plataforma = new Intent(PantallaDeSeleccion.this, PantallaAnyadirPlataforma.class);
                        startActivity(ir_anyadir_plataforma);
                        return true;
                    case R.id.PLATAFORMAS_menuItem:
                        Toast.makeText(PantallaDeSeleccion.this, "hola2", Toast.LENGTH_SHORT).show();
                        Intent ir_anyadir_pelicula = new Intent(PantallaDeSeleccion.this, PantallaAnyadirPelicula.class);
                        startActivity(ir_anyadir_pelicula);
                        return true;
                }
                return false;
            }
        });
        app.show();
    }




}