package com.example.esumhdadmmispelis77044099x.Adaptadores;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esumhdadmmispelis77044099x.Entities.Pelicula;
import com.example.esumhdadmmispelis77044099x.R;

import java.util.List;

public class Adaptador_Peliculas extends RecyclerView.Adapter<Adaptador_Peliculas.ViewHolder> {

    private List<Pelicula> mDataList;

    public Adaptador_Peliculas(List<Pelicula> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_peliculas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pelicula data = mDataList.get(position);

        byte[] imageByteArray = data.getCaratula();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
        holder.caratula.setImageBitmap(bitmap);

        holder.titulo.setText(data.getTitulo());
        holder.genero.setText(data.getGenero());
        holder.calificacion.setText(data.getCalificacion());
        holder.duracion.setText(data.getDuracion());

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id_pelis, titulo, genero, calificacion, duracion;
        ImageView caratula;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_pelis = itemView.findViewById(R.id.id_peliculas_container);
            caratula = itemView.findViewById(R.id.caratula_container);
            titulo = itemView.findViewById(R.id.titulo_container);
            genero = itemView.findViewById(R.id.genero_container);
            calificacion = itemView.findViewById(R.id.calificacion_container);
            duracion = itemView.findViewById(R.id.duracion_container);
        }
    }
}