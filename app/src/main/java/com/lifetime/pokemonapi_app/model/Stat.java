package com.lifetime.pokemonapi_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    //Note: array[0]: spd
    //Note: array[1]: sdef
    //Note: array[2]: satk
    //Note: array[3]: def
    //Note: array[4]: atk
    //Note: array[5]: hp
    @SerializedName("base_stat")
    @Expose
    private int baseStat;

    public int getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(int baseStat) {
        this.baseStat = baseStat;
    }
}
