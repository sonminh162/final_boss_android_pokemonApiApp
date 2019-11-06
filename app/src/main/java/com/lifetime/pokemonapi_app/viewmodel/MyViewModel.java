package com.lifetime.pokemonapi_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lifetime.pokemonapi_app.model.ResultForAll;
import com.lifetime.pokemonapi_app.model.Stat;
import com.lifetime.pokemonapi_app.retrofit.GetDataService;
import com.lifetime.pokemonapi_app.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends ViewModel {

    private GetDataService getDataService;

    public MutableLiveData<Stat[]> statsMutableLiveData = new MutableLiveData<>();

    public void init(){
        getDataService = RetrofitClientInstance.createService(GetDataService.class);
    }

    public LiveData<Stat[]> getStatsById(int id){
        getDataService.getPokemonFromId(id).enqueue(new Callback<ResultForAll>() {
            @Override
            public void onResponse(Call<ResultForAll> call, Response<ResultForAll> response) {
                    ResultForAll resultForAllResponse = response.body();
                    if(resultForAllResponse!=null){
                        statsMutableLiveData.setValue(resultForAllResponse.getStats());
                    }
            }

            @Override
            public void onFailure(Call<ResultForAll> call, Throwable t) {

            }
        });
        return statsMutableLiveData;
    }
}
