package com.lifetime.pokemonapi_app.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.common.base.CaseFormat;
import com.lifetime.pokemonapi_app.model.Move;
import com.lifetime.pokemonapi_app.model.ResultForAll;
import com.lifetime.pokemonapi_app.retrofit.GetDataService;
import com.lifetime.pokemonapi_app.retrofit.RetrofitClientInstance;
import com.lifetime.pokemonapi_app.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonMovesViewModel extends ViewModel {

    private GetDataService getDataService;

    public MutableLiveData<String[]> movesMutableLiveData = new MutableLiveData<>();

    public void init() {
        getDataService = RetrofitClientInstance.createService(GetDataService.class);
    }

    public void getMovesByPokemonId(int id) {
        getDataService.getPokemonFromId(id).enqueue(new Callback<ResultForAll>() {
            @Override
            public void onResponse(Call<ResultForAll> call, Response<ResultForAll> response) {
                ResultForAll resultForAllResponse = response.body();
                if (resultForAllResponse != null) {
                    Move[] moves = resultForAllResponse.getMoves();
                    String[] listMoveName = new String[moves.length];
                    for (int i = 0; i < moves.length; i++) {
                        String currentMoveName = moves[i].getMoveInfo().getName();
                        listMoveName[i] = Utils.splitCamelCase(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL,currentMoveName));
                    }
                    movesMutableLiveData.setValue(listMoveName);
                }
            }

            @Override
            public void onFailure(Call<ResultForAll> call, Throwable t) {

            }
        });
    }

    public void getMovesByPokemonName(String name) {
        getDataService.getPokemonFromName(name).enqueue(new Callback<ResultForAll>() {
            @Override
            public void onResponse(Call<ResultForAll> call, Response<ResultForAll> response) {
                ResultForAll resultForAllResponse = response.body();
                if (resultForAllResponse != null) {
                    Move[] moves = resultForAllResponse.getMoves();
                    String[] listMoveName = new String[moves.length];
                    for (int i = 0; i < moves.length; i++) {
                        String currentMoveName = moves[i].getMoveInfo().getName();
                        listMoveName[i] = Utils.splitCamelCase(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL,currentMoveName));
                    }
                    movesMutableLiveData.setValue(listMoveName);
                }
            }

            @Override
            public void onFailure(Call<ResultForAll> call, Throwable t) {

            }
        });
    }
}
