package com.lifetime.pokemonapi_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sprites {
    @SerializedName("front_default")
    @Expose
    private String linkUrl;

    public String getLinkUrl() {
        return linkUrl;
    }
}
