package com.lifetime.pokemonapi_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultForAll {

    @SerializedName("stats")
    @Expose
    private Stat[] stats;

    @SerializedName("forms")
    @Expose
    private Form[] forms;

    @SerializedName("moves")
    @Expose
    private Move[] moves;

    public Move[] getMoves() {
        return moves;
    }

    public Form[] getForms() {
        return forms;
    }

    public Stat[] getStats() {
        return stats;
    }

}
