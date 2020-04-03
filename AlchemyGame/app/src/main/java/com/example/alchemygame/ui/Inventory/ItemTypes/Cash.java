package com.example.alchemygame.ui.Inventory.ItemTypes;

public class Cash {
    public int cash;

    public Cash() {
        cash = 100;
    }

    public void updateCash(int nCash) {
        cash = nCash;
    }

    public int getCash() {
        return cash;
    }
}
