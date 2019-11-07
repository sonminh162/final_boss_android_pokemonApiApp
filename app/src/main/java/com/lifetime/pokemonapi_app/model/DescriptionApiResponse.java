package com.lifetime.pokemonapi_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DescriptionApiResponse {

    @SerializedName("flavor_text_entries")
    @Expose
    private Description[] descriptions;

    public Description[] getDescriptions() {
        return descriptions;
    }
}
