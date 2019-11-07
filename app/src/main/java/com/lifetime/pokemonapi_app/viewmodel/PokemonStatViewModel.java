package com.lifetime.pokemonapi_app.viewmodel;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lifetime.pokemonapi_app.model.ResultForAll;
import com.lifetime.pokemonapi_app.model.Stat;
import com.lifetime.pokemonapi_app.retrofit.GetDataService;
import com.lifetime.pokemonapi_app.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonStatViewModel extends ViewModel {

    private GetDataService getDataService;

    public MutableLiveData<Stat[]> statsMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> validResult = new MutableLiveData<>();

    public void init() {
        getDataService = RetrofitClientInstance.createService(GetDataService.class);
        validResult.setValue(false);
    }

    public void getStatsByPokemonId(int id, final Activity context) {
        getDataService.getPokemonFromId(id).enqueue(new Callback<ResultForAll>() {
            @Override
            public void onResponse(Call<ResultForAll> call, Response<ResultForAll> response) {
                ResultForAll resultForAllResponse = response.body();
                if (resultForAllResponse != null) {
                    statsMutableLiveData.setValue(resultForAllResponse.getStats());
                    validResult.setValue(true);
                } else {
                    Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultForAll> call, Throwable t) {

            }
        });
    }

    public void getStatsByPokemonName(String name, final Activity context) {
        getDataService.getPokemonFromName(name).enqueue(new Callback<ResultForAll>() {
            @Override
            public void onResponse(Call<ResultForAll> call, Response<ResultForAll> response) {
                ResultForAll resultForAllResponse = response.body();
                if (resultForAllResponse != null) {
                    statsMutableLiveData.setValue(resultForAllResponse.getStats());
                    validResult.setValue(true);
                } else {
                    Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultForAll> call, Throwable t) {

            }
        });
    }
}
