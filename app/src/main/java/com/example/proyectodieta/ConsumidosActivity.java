package com.example.proyectodieta;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ConsumidosActivity extends AppCompatActivity {

    private ListView listIngredientesConsumidos;
    private BaseDatosDieta db;
    private IngredienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumidos);

        db = new BaseDatosDieta(this);

        listIngredientesConsumidos = findViewById(R.id.listIngredientesConsumidos);

        cargarIngredientesConsumidos();
    }

    private void cargarIngredientesConsumidos() {
        List<Ingrediente> ingredientes = db.obtenerIngredientesConsumidos();
        // Usamos el constructor del adapter que deshabilita la selección
        adapter = new IngredienteAdapter(this, ingredientes, false);
        listIngredientesConsumidos.setAdapter(adapter);
    }
}
