package com.lifetime.pokemonapi_app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lifetime.pokemonapi_app.R;
import com.lifetime.pokemonapi_app.utils.Utils;
import com.lifetime.pokemonapi_app.viewmodel.PokemonStatViewModel;

import static com.lifetime.pokemonapi_app.constant.Constant.SEARCH_KEY;

public class SearchPokemonActivity extends AppCompatActivity {

    EditText searchText;
    PokemonStatViewModel pokemonStatViewModel;
    String searchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pokemon);

        searchText = findViewById(R.id.searchText);

        pokemonStatViewModel = ViewModelProviders.of(this).get(PokemonStatViewModel.class);
        pokemonStatViewModel.init();

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH
                        || i == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
                    searchKey = searchText.getText().toString().trim();

                    if (Utils.isInteger(searchKey)) {
                        pokemonStatViewModel.getStatsByPokemonId(Integer.parseInt(searchKey), SearchPokemonActivity.this);
                    } else {
                        pokemonStatViewModel.getStatsByPokemonName(searchKey, SearchPokemonActivity.this);
                    }
                }
                return false;
            }
        });

        // sau khi back lại màn search lần thứ 2, validResult vẫn là true, nhưng ko bị nhảy intent vì nó ko onChange
        // kích hoạt khi thỏa mãn cả 2 điều kiện đồng thời vừa onChanged vừa check boolean = true.
        pokemonStatViewModel.validResult.observe(this, new Observer<Boolean>() {
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
