package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Usuarios2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios2);
    }

    public void veruserspermitidos(View view) {
        Intent i =new Intent(this,Usuarios.class);
        i.putExtra("Permitido","Si");
        view.getContext().startActivity(i);
    }
    public void verusersdenegados(View view) {
        Intent i =new Intent(this,Usuarios.class);
        i.putExtra("Permitido","No");
        view.getContext().startActivity(i);
    }
}