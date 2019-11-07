package com.lifetime.pokemonapi_app.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lifetime.pokemonapi_app.model.Form;
import com.lifetime.pokemonapi_app.model.ResultForAll;
import com.lifetime.pokemonapi_app.retrofit.GetDataService;
import com.lifetime.pokemonapi_app.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonNameViewModel extends ViewModel {
    private GetDataService getDataService;

    public MutableLiveData<String> nameMutableLiveData = new MutableLiveData<>();

    public void init() {
        getDataService = RetrofitClientInstance.createService(GetDataService.class);
    }

    public void getNameByPokemonId(int id) {
        getDataService.getPokemonFromId(id).enqueue(new Callback<ResultForAll>() {
            @Override
            public void onResponse(Call<ResultForAll> call, Response<ResultForAll> response) {
                ResultForAll resultForAllResponse = response.body();
                if (resultForAllResponse != null) {
                    Form[] forms = resultForAllResponse.getForms();
                    nameMutableLiveData.setValue(forms[0].getName());
                }
            }

            @Override
            public void onFailure(Call<ResultForAll> call, Throwable t) {

            }
        });
    }

    public void getNameByPokemonName(String name) {
        getDataService.getPokemonFromName(name).enqueue(new Callback<ResultForAll>() {
            @Override
            public void onResponse(Call<ResultForAll> call, Response<ResultForAll> response) {
                ResultForAll resultForAllResponse = response.body();
                if (resultForAllResponse != null) {
                    Form[] forms = resultForAllResponse.getForms();
                    nameMutableLiveData.setValue(forms[0].getName());
                }
            }

            @Override
            public void onFailure(Call<ResultForAll> call, Throwable t) {

            }
        });
    }
}
