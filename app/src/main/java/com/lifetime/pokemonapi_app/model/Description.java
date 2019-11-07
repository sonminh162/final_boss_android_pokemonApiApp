package com.lifetime.pokemonapi_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Description {

    @SerializedName("flavor_text")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }
}
