package com.joseaguilar.llenarrecyclerview_con_imagenesglide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.joseaguilar.llenarrecyclerview_con_imagenesglide.Adapters.PokemonAdapter;
import com.joseaguilar.llenarrecyclerview_con_imagenesglide.ApiService.PokeApi;
import com.joseaguilar.llenarrecyclerview_con_imagenesglide.Clases.Pokemon;
import com.joseaguilar.llenarrecyclerview_con_imagenesglide.ControlRespuesta.PokemonRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
* SE PROGRAMA DE LA SIGUIENTE FORMA
*
* - RETROFIT
* - RECYCLERVIEW
* - GLIDE --> este se programara en el Adapter del RecyclerView
*
* */


public class MainActivity extends AppCompatActivity {
    /*Paso 1 RETROFIT : Creacion de la instancia global*/
    private Retrofit retrofit;
    private int offset;

    /*----------------------------------------------------*/
     /*Paso 1 RECYCLERVIEW : Creacion de la instancia global*/
    private RecyclerView recyclerView;
    private PokemonAdapter listaPokemonAdapter;
    private boolean aptoParaCargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*---------------------------------------------*/
        /*Paso 2 RETROFIT : Creacion de metodo retrofit para consumir el api rest*/
        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/") //la url base es la que no varia
                .addConverterFactory(GsonConverterFactory.create()) //hacemos la decodificacion del resultado a json
                .build();

        /*Paso 3 RETROFIT : Creamos metodo obtenerDatos() que recepcionara los datos del consumo */
        offset = 0; // se crea esta variable siempre y cuando en un listado haya una secuencia de carga de 20 en 20 imagenes
        obtenerDatos(offset);
        aptoParaCargar = true;

        /*---------------------------------------------*/
        /*Paso 2 RECYCLERVIEW : Creacion y configuracion del recyclerview*/
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaPokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3); //lo establecemos en modo grilla
        recyclerView.setLayoutManager(layoutManager);
        //agregamos este metodo addOnScrollListener, con la finalidad de agregar una barra scroll al recyclerview
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //Creamos esta condicional con la finalidad de que si llegamos al final de los 20 pokemon si hay mas en el arraylist
                //pueda seguir bajando y descargando las imagenes
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Toast.makeText(getApplicationContext(),"llegamos al final",Toast.LENGTH_SHORT).show();

                            aptoParaCargar = false;
                            offset += 20;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });
        /*---------------------------------------------*/


    }
    /*---------------------------------------------*/
    //Este metodo consumira el metodo que esta dentro de PokeApi
    private void obtenerDatos(int offset) {
        PokeApi service = retrofit.create(PokeApi.class);
       Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon(20, offset);
        //de esta forma podremos consumir todo
        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()) {
                    PokemonRespuesta pokemonRespuesta = response.body(); //recepcionamos la informacion
                    ArrayList<Pokemon> listaPokemon = pokemonRespuesta.getResults(); //creamos arraylist con la lista de pokemon
                    listaPokemonAdapter.adicionarListaPokemon(listaPokemon); //creamos un metodo en el ADAPTER del recyclerView
                } else {
                    Toast.makeText(getApplicationContext(), "Error onResponse", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                aptoParaCargar = true;
                Toast.makeText(getApplicationContext(), "Error onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*---------------------------------------------*/
}
