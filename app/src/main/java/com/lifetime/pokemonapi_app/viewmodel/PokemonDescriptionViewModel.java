package com.lifetime.pokemonapi_app.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lifetime.pokemonapi_app.model.Description;
import com.lifetime.pokemonapi_app.model.DescriptionApiResponse;
import com.lifetime.pokemonapi_app.retrofit.GetDataService;
import com.lifetime.pokemonapi_app.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDescriptionViewModel extends ViewModel {
    private GetDataService getDataService;

    public MutableLiveData<String> descriptionMutableLiveData = new MutableLiveData<>();

    public void init(){
        getDataService = RetrofitClientInstance.createService(GetDataService.class);
    }

    public void getDescriptionByPokemonId(int id){
        getDataService.getPokemonDescriptionFromId(id).enqueue(new Callback<DescriptionApiResponse>() {
            @Override
            public void onResponse(Call<DescriptionApiResponse> call, Response<DescriptionApiResponse> response) {
                DescriptionApiResponse descriptionApiResponse = response.body();
                if(descriptionApiResponse != null){
                    Description[] descriptions = descriptionApiResponse.getDescriptions();
                    descriptionMutableLiveData.setValue(descriptions[1].getDescription());
                }
            }

            @Override
            public void onFailure(Call<DescriptionApiResponse> call, Throwable t) {

            }
        });
    }

    public void getDescriptionByPokemonName(String name){
        getDataService.getPokemonDescriptionFromName(name).enqueue(new Callback<DescriptionApiResponse>() {
            @Override
            public void onResponse(Call<DescriptionApiResponse> call, Response<DescriptionApiResponse> response) {
                DescriptionApiResponse descriptionApiResponse = response.body();
                if(descriptionApiResponse != null){
                    Description[] descriptions = descriptionApiResponse.getDescriptions();
                    descriptionMutableLiveData.setValue(descriptions[1].getDescription());
                }
            }

            @Override
            public void onFailure(Call<DescriptionApiResponse> call, Throwable t) {

            }
        });
    }
}
