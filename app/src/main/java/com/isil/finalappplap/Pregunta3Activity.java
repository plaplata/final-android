package com.isil.finalappplap;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.isil.finalappplap.db.DbCantidades;
import com.isil.finalappplap.db.DbHelper;

public class Pregunta3Activity extends AppCompatActivity implements View.OnClickListener {
    EditText etSueldoBasico, etBonificacion;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta3);
        DbHelper dbHelper = new DbHelper(Pregunta3Activity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db != null){
            Toast.makeText(Pregunta3Activity.this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Pregunta3Activity.this, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
        }

        etSueldoBasico = findViewById(R.id.etSueldoBasico);
        etBonificacion = findViewById(R.id.etBonificacion);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        DbCantidades dbCantidades = new DbCantidades(Pregunta3Activity.this);
        long id = dbCantidades.insertaCantidad(Integer.parseInt(etSueldoBasico.getText().toString()),
                Integer.parseInt(etBonificacion.getText().toString()));
        if(id>0){
            Toast.makeText(Pregunta3Activity.this, "Registro guardado", Toast.LENGTH_SHORT).show();
            limpiar();
        }
        else{
            Toast.makeText(Pregunta3Activity.this, "Error al guardar registro", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiar(){
        etSueldoBasico.setText("");
        etBonificacion.setText("");

    }
}