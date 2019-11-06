package com.lifetime.pokemonapi_app.fragment;

import android.content.Intent;
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
import com.lifetime.pokemonapi_app.viewmodel.MyViewModel;

public class StatFragment extends Fragment {
    ProgressBar progressBar;

    private View view;

    private MyViewModel myViewModel;

    public StatFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stat_fragment,container,false);

//        initView();

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.init();

        if(getActivity()!=null){
            int id = getActivity().getIntent().getExtras().getInt("id");
            myViewModel.getStatsById(id);
        }

        myViewModel.statsMutableLiveData.observe(this, new Observer<Stat[]>() {
            @Override
            public void onChanged(Stat[] stats) {
                updateViewStats(stats);
            }
        });

        return view;
    }

    private void updateViewStats(Stat[] stats){
        progressBar = view.findViewById(R.id.hpProgressBar);
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

//    private void initView(){
//        progressBar = view.findViewById(R.id.hpProgressBar);
//        progressBar.setProgress(37);
//        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));
//
//        progressBar = view.findViewById(R.id.atkProgressBar);
//        progressBar.setProgress(14);
//        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));
//
//        progressBar = view.findViewById(R.id.defProgressBar);
//        progressBar.setProgress(41);
//        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));
//
//        progressBar = view.findViewById(R.id.satkProgressBar);
//        progressBar.setProgress(74);
//        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));
//
//        progressBar = view.findViewById(R.id.sdefProgressBar);
//        progressBar.setProgress(25);
//        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));
//
//        progressBar = view.findViewById(R.id.spdProgressBar);
//        progressBar.setProgress(51);
//        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.background_progress));
//    }
}
