package com.example.esumhdadmmispelis77044099x;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esumhdadmmispelis77044099x.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    TextView singUp;

    DbHelper dbInstance = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = dbInstance.consultarUsuPas(username.getText().toString(),password.getText().toString());
                //username.getText().toString().equals("user") && password.getText().toString().equals("1234")
                if ( cursor.getCount() > 0) {
                    Toast.makeText(MainActivity.this, "Éxito iniciar sesión", Toast.LENGTH_SHORT).show();
                    loginButton(view);
                } else {
                    Toast.makeText(MainActivity.this, "Fallo al iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });


        singUp = (TextView) findViewById(R.id.signUpText);
        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singUpIntent = new Intent(MainActivity.this, PantallaRegistro.class);
                startActivity(singUpIntent);
            }
        });
    }
    public void loginButton(View view){
        Intent login = new Intent(this, PantallaDeSeleccion.class);
        startActivity(login);
    }
}