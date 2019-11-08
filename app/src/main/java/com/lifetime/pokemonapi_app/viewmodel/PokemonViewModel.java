package com.lifetime.pokemonapi_app.viewmodel;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.common.base.CaseFormat;
import com.lifetime.pokemonapi_app.model.Description;
import com.lifetime.pokemonapi_app.model.DescriptionApiResponse;
import com.lifetime.pokemonapi_app.model.Form;
import com.lifetime.pokemonapi_app.model.ImageResponse;
import com.lifetime.pokemonapi_app.model.Move;
import com.lifetime.pokemonapi_app.model.ResultForAll;
import com.lifetime.pokemonapi_app.model.Sprites;
import com.lifetime.pokemonapi_app.model.Stat;
import com.lifetime.pokemonapi_app.retrofit.PokemonApi;
import com.lifetime.pokemonapi_app.retrofit.RetrofitClientInstance;
import com.lifetime.pokemonapi_app.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonViewModel extends ViewModel {

    private PokemonApi pokemonApi;

    public MutableLiveData<Stat[]> statsMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> descriptionMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> urlImageMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String[]> movesMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> nameMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> validResult = new MutableLiveData<>();

    public void init() {
        pokemonApi = RetrofitClientInstance.createService(PokemonApi.class);
        validResult.setValue(false);
    }

    public void getStats(String name, final Activity context) {
        pokemonApi.getPokemon(name).enqueue(new Callback<ResultForAll>() {
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

    public void getDescription(String name){
        pokemonApi.getPokemonDescription(name).enqueue(new Callback<DescriptionApiResponse>() {
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

    public void getUrlImage(String name){
        pokemonApi.getPokemonImage(name).enqueue(new Callback<ImageResponse>() {
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

    public void getMoves(String name) {
        pokemonApi.getPokemon(name).enqueue(new Callback<ResultForAll>() {
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

    public void getName(String name) {
        pokemonApi.getPokemon(name).enqueue(new Callback<ResultForAll>() {
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
