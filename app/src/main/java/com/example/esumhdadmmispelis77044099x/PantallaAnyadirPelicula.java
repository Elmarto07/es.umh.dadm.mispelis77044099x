package com.example.esumhdadmmispelis77044099x;

import androidx.activity.result.ActivityResultLauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Bitmap;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;

import android.provider.MediaStore;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.esumhdadmmispelis77044099x.Entities.Image;
import com.example.esumhdadmmispelis77044099x.db.DbPeliculas;


import java.io.IOException;
import java.util.ArrayList;

public class PantallaAnyadirPelicula extends AppCompatActivity {

    private ActivityResultLauncher<Intent> imgCamera;
    private ActivityResultLauncher<String> imgUri;
    private Bitmap bitmap;
    private ImageView imgSeleccionada, imgObtener;
    //private RecyclerView imgObtener;


    Button cancelButton, signUpBtn, imageViewIdBtn;

    EditText titulo, calificacion, duracion;

    Spinner dropDown;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int STORAGE_PERMISSION_CODE = 23;

        setContentView(R.layout.activity_pantalla_anyadir_pelicula);

        //Permission to access external storage
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

        titulo = findViewById(R.id.get_titulo);
        calificacion = findViewById(R.id.get_calificacion);
        duracion = findViewById(R.id.get_duracion);


        signUpBtn = findViewById(R.id.filmSingupButton);
        cancelButton = findViewById(R.id.filmCancelButton);
        imageViewIdBtn = findViewById(R.id.imageViewIdBtn);

        imgSeleccionada = findViewById(R.id.imgSeleccionada);

        dropDown = findViewById(R.id.dropdownGender);


        activityLauncher();


        ArrayList<Gender> gender = new ArrayList<>();

        gender.add(new Gender(1, "Masculino"));
        gender.add(new Gender(2, "Femenino"));

        ArrayAdapter<Gender> adapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, gender);

        dropDown.setAdapter(adapter);

        dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        imageViewIdBtn.setOnClickListener((view) -> mostrarOpciones(view));


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent( PantallaAnyadirPelicula.this, PantallaDeSeleccion.class);
                startActivity(cancelIntent);
            }
        });




        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbPeliculas dbPeliculas = new DbPeliculas(PantallaAnyadirPelicula.this);

                Bitmap imagen = ((BitmapDrawable) imgSeleccionada.getDrawable()).getBitmap();

                Image img = new Image();
                img.setFoto(imagen);

                Gender varG = (Gender) dropDown.getSelectedItem();

                long id = dbPeliculas.insertarPeliculas(titulo.getText().toString(), varG.getGender(), calificacion.getText().toString(), duracion.getText().toString(), img);
                if ( id > 0) {
                    Toast.makeText(PantallaAnyadirPelicula.this, "PELICULA GUARDADA", Toast.LENGTH_SHORT).show();

                    Intent cancelIntent = new Intent( PantallaAnyadirPelicula.this, PantallaDeSeleccion.class);
                    startActivity(cancelIntent);
                } else {
                    Toast.makeText(PantallaAnyadirPelicula.this, "PELICULA NO GUARDADA", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




    public void mostrarOpciones(View v) {
        PopupMenu pp = new PopupMenu(this, imageViewIdBtn);
        pp.getMenuInflater().inflate(R.menu.menupopupmetodocapturaimg, pp.getMenu());
        pp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.CAMARA:
                        Toast.makeText(PantallaAnyadirPelicula.this, "hola", Toast.LENGTH_SHORT).show();
                        tomarFoto();
                        return true;
                    case R.id.GALERIA:
                        Toast.makeText(PantallaAnyadirPelicula.this, "hola2", Toast.LENGTH_SHORT).show();
                        openGallery();
                        return true;
                }
                return false;
            }
        });
        pp.show();
    }

    private void activityLauncher() {
        imgUri = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                result -> {
                    imgSeleccionada.setImageURI(result);
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        imgCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Bundle bundle;
                    if (result.getData() != null) {
                        bundle = result.getData().getExtras();
                        bitmap = (Bitmap) bundle.get("data");
                        imgSeleccionada.setImageBitmap(bitmap);
                    }
                }
        );
    }

    private void tomarFoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imgCamera.launch(intent);
    }

    private void openGallery() {

        imgUri.launch("image/*");
    }


}