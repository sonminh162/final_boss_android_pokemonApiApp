package com.lifetime.pokemonapi_app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lifetime.pokemonapi_app.R;
import com.lifetime.pokemonapi_app.fragment.StatFragment;

import java.util.ArrayList;
import java.util.List;

public class PokemonInformationActivity extends AppCompatActivity {

    Button moveBtn,evolutionBtn,statBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_activity_main);

        moveBtn = findViewById(R.id.movesButton);
        evolutionBtn = findViewById(R.id.evolutionButton);
        statBtn = findViewById(R.id.statButton);

        setUpUI();

    }

    private void setUpUI(){

        loadFragment(new StatFragment());

        setUpFragmentFeature();

        findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpFragmentFeature(){
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
            }
        });

        statBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttons.get(0).setSelected(true);
                buttons.get(1).setSelected(false);
                buttons.get(2).setSelected(false);
                queryButton(buttons);

                loadFragment(new StatFragment());
            }
        });

        evolutionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttons.get(1).setSelected(true);
                buttons.get(0).setSelected(false);
                buttons.get(2).setSelected(false);
                queryButton(buttons);
            }
        });
    }

    private void queryButton(List<Button> buttons){
        for(int i = 0; i< buttons.size(); i++){
            Button currentButton = buttons.get(i);
            if(currentButton.isSelected()){
                currentButton.setBackground(getResources().getDrawable(R.drawable.button_box));
                currentButton.setTextColor(getResources().getColor(R.color.white_two));
            } else {
                currentButton.setBackground(getResources().getDrawable(R.drawable.background_box));
                currentButton.setTextColor(getResources().getColor(R.color.dark_blue));
            }
        }
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}
