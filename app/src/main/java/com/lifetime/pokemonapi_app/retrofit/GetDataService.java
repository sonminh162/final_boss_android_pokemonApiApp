package com.lifetime.pokemonapi_app.retrofit;

import com.lifetime.pokemonapi_app.model.DescriptionApiResponse;
import com.lifetime.pokemonapi_app.model.ResultForAll;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("pokemon/{id}/")
    Call<ResultForAll> getPokemonFromId(@Path("id") int id);

    @GET("pokemon/{name}/")
    Call<ResultForAll> getPokemonFromName(@Path("name") String name);

    @GET("pokemon-species/{id}/")
    Call<DescriptionApiResponse> getPokemonDescriptionFromId(@Path("id") int id);

    @GET("pokemon-species/{name}/")
    Call<DescriptionApiResponse> getPokemonDescriptionFromName(@Path("name") String name);


}
