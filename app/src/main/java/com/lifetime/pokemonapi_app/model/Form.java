package com.lifetime.pokemonapi_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Form {
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }
}
