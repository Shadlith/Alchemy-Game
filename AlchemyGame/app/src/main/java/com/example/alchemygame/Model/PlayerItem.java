package com.example.alchemygame.Model;

public class PlayerItem {

    private int idValue;
    private int xp;
    private String buff;

    public PlayerItem() {
        idValue = 0;
        xp = 0;
        buff = "";
    }

    public PlayerItem(int nID, int nXP, String nBuff) {
        idValue = nID;
        xp = nXP;
        buff = nBuff;
    }

    public int getIdValue() {
        return idValue;
    }

    public int getXp() {
        return xp;
    }

    public String getBuff() {
        return buff;
    }
}
