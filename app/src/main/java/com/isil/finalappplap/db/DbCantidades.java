package com.isil.finalappplap.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbCantidades extends DbHelper{

    Context context;

    public DbCantidades(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaCantidad(double sueldobasico, double bonificacion){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("sueldobasico", sueldobasico);
            values.put("bonificacion", bonificacion);

            id = db.insert(TABLE_SUELDO, null, values);
        }
        catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
