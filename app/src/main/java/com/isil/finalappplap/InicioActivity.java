package com.isil.finalappplap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mbtnPregunta1 = findViewById(R.id.btnPregunta1);
        Button mbtnPregunta2 = findViewById(R.id.btnPregunta2);
        Button mbtnPregunta3 = findViewById(R.id.btnPregunta3);

        mbtnPregunta1.setOnClickListener(this);
        mbtnPregunta2.setOnClickListener(this);
        mbtnPregunta3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnPregunta1: mostrarCajasActivity(); break;
            case R.id.btnPregunta2: mostrarNavigationActivity(); break;
            case R.id.btnPregunta3: mostrarPregunta3(); break;
        }
    }

    private void mostrarPregunta3() {
        startActivity(new Intent(this, Pregunta3Activity.class));
    }

    private void mostrarNavigationActivity() {
        startActivity(new Intent(this, NavigationActivity.class));
    }

    private void mostrarCajasActivity() {
        startActivity(new Intent(this, CajasActivity.class));
    }
}