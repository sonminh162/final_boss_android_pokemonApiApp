package com.lifetime.pokemonapi_app.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lifetime.pokemonapi_app.model.ImageResponse;
import com.lifetime.pokemonapi_app.model.Sprites;
import com.lifetime.pokemonapi_app.retrofit.GetDataService;
import com.lifetime.pokemonapi_app.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonImageViewModel extends ViewModel {
    private GetDataService getDataService;

    public MutableLiveData<String> urlImageMutableLiveData = new MutableLiveData<>();

    public void init(){
        getDataService = RetrofitClientInstance.createService(GetDataService.class);
    }

    public void getUrlImageByPokemonId(int id){
        getDataService.getPokemonImageFromId(id).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                ImageResponse imgResponse = response.body();
                if(imgResponse!=null){
                    Sprites sprites = imgResponse.getSprites();
                    urlImageMutableLiveData.setValue(sprites.getLinkUrl());
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }

    public void getUrlImageByPokemonName(String name){
        getDataService.getPokemonImageFromName(name).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                ImageResponse imgResponse = response.body();
                if(imgResponse!=null){
                    Sprites sprites = imgResponse.getSprites();
                    urlImageMutableLiveData.setValue(sprites.getLinkUrl());
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }
}
