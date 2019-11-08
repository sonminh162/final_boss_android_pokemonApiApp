package com.lifetime.pokemonapi_app.retrofit;

import com.lifetime.pokemonapi_app.model.DescriptionApiResponse;
import com.lifetime.pokemonapi_app.model.ImageResponse;
import com.lifetime.pokemonapi_app.model.ResultForAll;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApi {

    @GET("pokemon/{key}/")
    Call<ResultForAll> getPokemon(@Path("key") String key);

    @GET("pokemon-species/{key}/")
    Call<DescriptionApiResponse> getPokemonDescription(@Path("key") String key);

    @GET("pokemon-form/{key}/")
    Call<ImageResponse> getPokemonImage(@Path("key") String key);



}
