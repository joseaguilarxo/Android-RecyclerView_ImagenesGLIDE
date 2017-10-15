package com.joseaguilar.llenarrecyclerview_con_imagenesglide.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.joseaguilar.llenarrecyclerview_con_imagenesglide.Clases.Pokemon;
import com.joseaguilar.llenarrecyclerview_con_imagenesglide.R;

import java.util.ArrayList;

/**
 * Created by josea on 15/10/2017.
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder>{
    private ArrayList<Pokemon> dataset;
    private Context context;

    public PokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }
    //--------------------------------------------------------------------------------
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonAdapter.ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());

        //EN EL ADAPTER SE RECIBIRA LAS IMAGENES POR MEDIO DE GLIDE
        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoImageView;
        private TextView nombreTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
        }
    }
    //--------------------------------------------------------------------------------

    //Este es el metodo que se agrego por el metodo obtenerDatos del mainactivity
    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }
    //--------------------------------------------------------------------------------
}
