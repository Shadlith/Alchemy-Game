package com.example.alchemygame.Model;

public class IngredientItem {
    private int IDValue;
    private String type;
    private String quality;
    private String value;

    public IngredientItem(){
        IDValue = 0;
        type = "Placeholder";
        quality = "0";
        value = "0";
    }

    public IngredientItem(int nIDVal, String nType, String nQual, String nVal){
        IDValue = nIDVal;
        type = nType;
        quality = nQual;
        value = nVal;
    }

    public int getIDValue(){
        return IDValue;
    }

    public String getType(){
        return type;
    }

    public String getQuality(){
        return quality;
    }

    public String getValue(){
        return value;
    }

}
