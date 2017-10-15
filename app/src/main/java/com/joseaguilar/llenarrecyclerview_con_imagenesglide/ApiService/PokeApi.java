package com.joseaguilar.llenarrecyclerview_con_imagenesglide.ApiService;

import com.joseaguilar.llenarrecyclerview_con_imagenesglide.ControlRespuesta.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by josea on 15/10/2017.
 */

public interface PokeApi {
    @GET("pokemon")
    //Metodo obtenerListaPokemon (este sera consumido en MainActivity - obtenerDatos() )
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);
}
