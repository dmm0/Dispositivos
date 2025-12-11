package com.example.proyectodieta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DietaAdapter extends BaseAdapter {

    private Context context;
    private List<Dieta> lista;

    public DietaAdapter(Context context, List<Dieta> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getIdDieta();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_dieta, parent, false);
        }

        Dieta d = lista.get(position);

        ImageView img = view.findViewById(R.id.imgDieta);
        TextView nombre = view.findViewById(R.id.txtNombreDieta);

        img.setImageResource(d.getImagen());
        nombre.setText(d.getNombre());

        return view;
    }
}
