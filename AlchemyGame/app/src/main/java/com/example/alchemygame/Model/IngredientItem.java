package com.example.alchemygame.Model;

public class IngredientItem {
    private int IDValue;
    private String type;
    private int quality;
    private int value;

    public IngredientItem(){
        IDValue = 0;
        type = "Placeholder";
        quality = 0;
        value = 0;
    }

    public IngredientItem(int nIDVal, String nType, int nQual, int nVal){
        IDValue = nIDVal;
        type = nType;
        quality = nQual;
        value = nVal;
    }

}
