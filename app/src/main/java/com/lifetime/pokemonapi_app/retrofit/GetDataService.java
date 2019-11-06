package com.lifetime.pokemonapi_app.retrofit;

import com.lifetime.pokemonapi_app.model.ResultForAll;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("{id}/")
    Call<ResultForAll> getPokemonFromId(@Path("id") int id);
}
