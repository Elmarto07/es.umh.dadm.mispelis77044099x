package com.example.esumhdadmmispelis77044099x;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.esumhdadmmispelis77044099x.db.DbHelper;
import com.example.esumhdadmmispelis77044099x.db.DbUsuarios;


public class PantallaRegistro extends AppCompatActivity {

    Button cancelButton, signUpBtn, createBtn;

    EditText email, pswrd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);

        email = findViewById(R.id.get_email);
        pswrd = findViewById(R.id.get_password);

        //TODO: Hacer validaciones al registrar al usuario

        cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent( PantallaRegistro.this, MainActivity.class);
                startActivity(cancelIntent);
            }
        });

        signUpBtn = (Button) findViewById(R.id.singupButton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbUsuarios dbUsuarios = new DbUsuarios(PantallaRegistro.this);
                long id = dbUsuarios.insertarUsuario(email.getText().toString(), pswrd.getText().toString());

                if ( id > 0) {
                    Toast.makeText(PantallaRegistro.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                    Intent cancelIntent = new Intent( PantallaRegistro.this, MainActivity.class);
                    startActivity(cancelIntent);
                } else {
                    Toast.makeText(PantallaRegistro.this, "REGISTRO NO GUARDADO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        createBtn = (Button) findViewById(R.id.createButton);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(PantallaRegistro.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db != null) {
                    Toast.makeText(PantallaRegistro.this, "BASE DE DATOS CREADA", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PantallaRegistro.this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void limpiar () {
        email.setText("");
        pswrd.setText("");
    }
}