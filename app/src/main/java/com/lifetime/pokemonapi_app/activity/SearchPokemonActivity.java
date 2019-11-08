package com.lifetime.pokemonapi_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lifetime.pokemonapi_app.R;
import com.lifetime.pokemonapi_app.viewmodel.PokemonViewModel;

import static com.lifetime.pokemonapi_app.constant.Constant.SEARCH_KEY;

public class SearchPokemonActivity extends AppCompatActivity {

    EditText searchText;
    PokemonViewModel pokemonViewModel;
    String searchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pokemon);

        searchText = findViewById(R.id.searchText);

        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        pokemonViewModel.init();

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH
                        || i == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
                    searchKey = searchText.getText().toString().trim();

                    pokemonViewModel.getStats(searchKey, SearchPokemonActivity.this);

                }
                return false;
            }
        });

        // sau khi back lại màn search lần thứ 2, validResult vẫn là true, nhưng ko bị nhảy intent vì nó ko onChange
        // kích hoạt khi thỏa mãn cả 2 điều kiện đồng thời vừa onChanged vừa check boolean = true.
        pokemonViewModel.validResult.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Intent intent = new Intent(SearchPokemonActivity.this, PokemonInformationActivity.class);
                    intent.putExtra(SEARCH_KEY, searchKey);
                    startActivity(intent);
                }
            }
        });
    }

}
