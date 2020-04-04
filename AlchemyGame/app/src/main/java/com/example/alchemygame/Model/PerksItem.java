package com.example.alchemygame.Model;

public class PerksItem {

    private int idValue;
    private String name;
    private String effect;

    public PerksItem() {
        idValue = 0;
        name = "";
        effect = "";
    }

    public PerksItem(int nID, String nName, String nEffect) {
        idValue = nID;
        name = nName;
        effect = nEffect;
    }

    public int getIdValue() {
        return idValue;
    }

    public String getPerkName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }
}
