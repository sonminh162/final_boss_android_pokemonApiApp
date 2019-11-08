package com.lifetime.pokemonapi_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.pokemonapi_app.R;
import com.lifetime.pokemonapi_app.adapter.MoveAdapter;
import com.lifetime.pokemonapi_app.model.MoveInfo;
import com.lifetime.pokemonapi_app.viewmodel.PokemonViewModel;

import java.util.ArrayList;

public class MoveFragment extends Fragment {
    private String searchKey;

    private View view;

    public static MoveFragment instance(String searchKey) {
        MoveFragment moveFragment = new MoveFragment();
        moveFragment.searchKey = searchKey;
        return moveFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.move_fragment, container, false);

        PokemonViewModel pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        pokemonViewModel.init();

        pokemonViewModel.getMoves(searchKey);

        pokemonViewModel.movesMutableLiveData.observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {
                initView(strings);
            }
        });

        return view;
    }

    private void initView(String[] strings) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_move);
        recyclerView.hasFixedSize();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<MoveInfo> moveInfos = new ArrayList<>();
        for (String string : strings) {
            moveInfos.add(new MoveInfo(string));
        }

        MoveAdapter adapter = new MoveAdapter(moveInfos);
        recyclerView.setAdapter(adapter);
    }
}