package com.lifetime.pokemonapi_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lifetime.pokemonapi_app.R;
import com.lifetime.pokemonapi_app.model.Stat;
import com.lifetime.pokemonapi_app.utils.Utils;
import com.lifetime.pokemonapi_app.viewmodel.PokemonStatViewModel;

public class StatFragment extends Fragment {
    private String searchKey;

    private View view;

    public static StatFragment instance(String searchKey) {
        StatFragment statFragment = new StatFragment();
        statFragment.searchKey = searchKey;
        return statFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stat_fragment, container, false);

        PokemonStatViewModel pokemonStatViewModel = ViewModelProviders.of(this).get(PokemonStatViewModel.class);
        pokemonStatViewModel.init();

        if (Utils.isInteger(searchKey)) {
            pokemonStatViewModel.getStatsByPokemonId(Integer.parseInt(searchKey), getActivity());
        } else {
            pokemonStatViewModel.getStatsByPokemonName(searchKey, getActivity());
        }

        pokemonStatViewModel.statsMutableLiveData.observe(this, new Observer<Stat[]>() {
            @Override
            public void onChanged(Stat[] stats) {
                updateViewStats(stats);
            }
        });

        return view;
    }

    private void updateViewStats(Stat[] stats) {
        ProgressBar progressBar = view.findViewById(R.id.hpProgressBar);
        progressBar.setProgress(stats[5].getBaseStat());
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));

        progressBar = view.findViewById(R.id.atkProgressBar);
        progressBar.setProgress(stats[4].getBaseStat());
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));

        progressBar = view.findViewById(R.id.defProgressBar);
        progressBar.setProgress(stats[3].getBaseStat());
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));

        progressBar = view.findViewById(R.id.satkProgressBar);
        progressBar.setProgress(stats[2].getBaseStat());
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));

        progressBar = view.findViewById(R.id.sdefProgressBar);
        progressBar.setProgress(stats[1].getBaseStat());
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));

        progressBar = view.findViewById(R.id.spdProgressBar);
        progressBar.setProgress(stats[0].getBaseStat());
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));
    }

}
//        myViewModel.getStatsByPokemonId(Integer.parseInt(getActivity().getIntent().getExtras().getString("id")));