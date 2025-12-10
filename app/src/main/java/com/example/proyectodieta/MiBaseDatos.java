package com.example.proyectodieta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MiBaseDatos extends SQLiteOpenHelper{
    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "dieta_control.db";
    private static final String TABLA_DIETAS = "dietas";
    private static final String TABLA_PLATILLOS = "platillos";
    private static final String TABLA_INGREDIENTES = "ingredientes";
    private static final String TABLA_RECETA = "no_receta";

    // Sentencias SQL de Creación
    private static final String SQL_CREAR_DIETAS = "CREATE TABLE " + TABLA_DIETAS + " (" +
            "id_dieta INTEGER PRIMARY KEY, " +
            "tipo TEXT)";
    private static final String SQL_CREAR_INGREDIENTES = "CREATE TABLE " + TABLA_INGREDIENTES + " (" +
            "id_ingrediente INTEGER PRIMARY KEY, " +
            "nombre TEXT)";
    private static final String SQL_CREAR_PLATILLOS = "CREATE TABLE " + TABLA_PLATILLOS + " (" +
            "id_platillo INTEGER PRIMARY KEY, " +
            "id_dieta INTEGER, " +
            "nombre TEXT, " +
            "FOREIGN KEY (id_dieta) REFERENCES " + TABLA_DIETAS + "(id_dieta))";
    private static final String SQL_CREAR_RECETA = "CREATE TABLE " + TABLA_RECETA + " (" +
            "id_receta INTEGER PRIMARY KEY, " +
            "id_platillo INTEGER, " +
            "id_ingrediente INTEGER, " +
            "cantidad REAL, " +
            "FOREIGN KEY (id_platillo) REFERENCES " + TABLA_PLATILLOS + "(id_platillo), " +
            "FOREIGN KEY (id_ingrediente) REFERENCES " + TABLA_INGREDIENTES + "(id_ingrediente))";

    public MiBaseDatos(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAR_DIETAS);
        db.execSQL(SQL_CREAR_PLATILLOS);
        db.execSQL(SQL_CREAR_INGREDIENTES);
        db.execSQL(SQL_CREAR_RECETA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar tablas en orden inverso a la creación (para respetar FKs)
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_RECETA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PLATILLOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_INGREDIENTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_DIETAS);
        onCreate(db);
    }
}

