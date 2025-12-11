package com.example.proyectodieta;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DetallePlatilloActivity extends AppCompatActivity {

    private TextView txtNombrePlatillo;
    private ListView listIngredientes;
    private Button btnPreparar;

    private BaseDatosDieta db;
    private Platillo platillo;
    private List<Ingrediente> ingredientes;
    private IngredienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_platillo);

        db = new BaseDatosDieta(this);

        // Obtener ID del platillo desde el Intent
        int idPlatillo = getIntent().getIntExtra("ID_PLATILLO", -1);
        if (idPlatillo == -1) {
            Toast.makeText(this, "Error: Platillo no encontrado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        platillo = db.obtenerPlatillo(idPlatillo);
        if (platillo == null) {
            Toast.makeText(this, "Error: Platillo no encontrado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Inicializar vistas
        txtNombrePlatillo = findViewById(R.id.txtNombrePlatillo);
        listIngredientes = findViewById(R.id.listIngredientes);
        btnPreparar = findViewById(R.id.btnPreparar);

        // Rellenar datos
        txtNombrePlatillo.setText(platillo.getNombre());
        cargarIngredientes();

        // Listener para el botón de preparar
        btnPreparar.setOnClickListener(v -> {
            prepararPlatillo();
            Toast.makeText(this, "¡Platillo preparado! Ingredientes marcados.", Toast.LENGTH_SHORT).show();
            finish(); // Volver a la pantalla anterior
        });
    }

    private void cargarIngredientes() {
        ingredientes = db.obtenerIngredientesDePlatillo(platillo.getIdPlatillo());
        adapter = new IngredienteAdapter(this, ingredientes);
        listIngredientes.setAdapter(adapter);
    }

    private void prepararPlatillo() {
        for (Ingrediente i : ingredientes) {
            db.marcarIngredienteConsumido(i.getIdIngrediente());
        }
    }
}
