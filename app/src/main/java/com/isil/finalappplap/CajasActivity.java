package com.isil.finalappplap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CajasActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText metLatitud;
    private EditText metLongitud;
    private EditText metMensaje;

    Button mbtnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajas);

        metLatitud = findViewById(R.id.edtLat);
        metLongitud = findViewById(R.id.edtLong);
        metMensaje = findViewById(R.id.edtMensaje);
        mbtnEnviar = findViewById(R.id.btnEnviar);

        mbtnEnviar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("latitud", metLatitud.getText().toString());
        intent.putExtra("longitud", metLongitud.getText().toString());
        intent.putExtra("mensaje", metMensaje.getText().toString());
        startActivity(intent);


    }
}