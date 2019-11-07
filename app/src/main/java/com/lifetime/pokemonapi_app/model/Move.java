package com.lifetime.pokemonapi_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Move {

    @SerializedName("move")
    @Expose
    private MoveInfo moveInfo;

    public MoveInfo getMoveInfo() {
        return moveInfo;
    }
}
