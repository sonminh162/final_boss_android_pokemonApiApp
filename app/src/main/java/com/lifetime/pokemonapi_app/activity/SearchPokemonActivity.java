package com.lifetime.pokemonapi_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lifetime.pokemonapi_app.R;
import com.lifetime.pokemonapi_app.viewmodel.MyViewModel;

public class SearchPokemonActivity extends AppCompatActivity {

    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pokemon);

        searchText = findViewById(R.id.searchText);
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH
                || i == EditorInfo.IME_ACTION_DONE
                || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){
                    Intent intent = new Intent(SearchPokemonActivity.this, PokemonInformationActivity.class);
                    intent.putExtra("id",searchText.getText().toString());
                    startActivity(intent);
                }
                return false;
            }
        });
    }

}
