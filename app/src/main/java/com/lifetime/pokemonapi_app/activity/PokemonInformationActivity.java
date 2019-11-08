package com.lifetime.pokemonapi_app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.common.base.CaseFormat;
import com.lifetime.pokemonapi_app.R;
import com.lifetime.pokemonapi_app.fragment.EvolutionFragment;
import com.lifetime.pokemonapi_app.fragment.MoveFragment;
import com.lifetime.pokemonapi_app.fragment.StatFragment;
import com.lifetime.pokemonapi_app.viewmodel.PokemonViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.lifetime.pokemonapi_app.constant.Constant.SEARCH_KEY;

public class PokemonInformationActivity extends AppCompatActivity {

    Button moveBtn, evolutionBtn, statBtn;

    ImageView pokemonView;

    PokemonViewModel pokemonViewModel;

    String searchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_activity_main);

        moveBtn = findViewById(R.id.movesButton);
        evolutionBtn = findViewById(R.id.evolutionButton);
        statBtn = findViewById(R.id.statButton);
        pokemonView = findViewById(R.id.pokemonView);

        searchKey = Objects.requireNonNull(getIntent().getExtras()).getString(SEARCH_KEY);

        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        pokemonViewModel.init();

        pokemonViewModel.getName(searchKey);
        pokemonViewModel.getDescription(searchKey);
        pokemonViewModel.getUrlImage(searchKey);

        setUpUI();
    }

    private void setUpUI() {

        loadFragment(StatFragment.instance(searchKey));

        setUpFragmentFeature();

        findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pokemonViewModel.nameMutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView name = findViewById(R.id.title);
                name.setText(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, s));
            }
        });

        pokemonViewModel.descriptionMutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView description = findViewById(R.id.description);
                description.setText(s);
            }
        });

        pokemonViewModel.urlImageMutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Picasso.get()
                        .load(s)
                        .into(pokemonView);
            }
        });
    }

    private void setUpFragmentFeature() {
        final List<Button> buttons = new ArrayList<>();
        buttons.add(statBtn);
        buttons.add(evolutionBtn);
        buttons.add(moveBtn);

        moveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttons.get(2).setSelected(true);
                buttons.get(0).setSelected(false);
                buttons.get(1).setSelected(false);
                queryButton(buttons);

                loadFragment(MoveFragment.instance(searchKey));
            }
        });

        statBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttons.get(0).setSelected(true);
                buttons.get(1).setSelected(false);
                buttons.get(2).setSelected(false);
                queryButton(buttons);

                loadFragment(StatFragment.instance(searchKey));
            }
        });

        evolutionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttons.get(1).setSelected(true);
                buttons.get(0).setSelected(false);
                buttons.get(2).setSelected(false);
                queryButton(buttons);

                loadFragment(new EvolutionFragment());
            }
        });
    }

    private void queryButton(List<Button> buttons) {
        for (int i = 0; i < buttons.size(); i++) {
            Button currentButton = buttons.get(i);
            if (currentButton.isSelected()) {
                currentButton.setBackground(getResources().getDrawable(R.drawable.button_box));
                currentButton.setTextColor(getResources().getColor(R.color.white_two));
            } else {
                currentButton.setBackground(getResources().getDrawable(R.drawable.background_box));
                currentButton.setTextColor(getResources().getColor(R.color.dark_blue));
            }
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}
