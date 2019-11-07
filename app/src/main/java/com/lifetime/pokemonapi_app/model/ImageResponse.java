package com.lifetime.pokemonapi_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageResponse {
    @SerializedName("sprites")
    @Expose
    private Sprites sprites;

    public Sprites getSprites() {
        return sprites;
    }
}
