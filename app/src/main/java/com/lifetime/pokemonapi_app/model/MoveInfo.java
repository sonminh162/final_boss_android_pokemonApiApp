package com.lifetime.pokemonapi_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoveInfo {

    @SerializedName("name")
    @Expose
    private String name;

    public MoveInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
