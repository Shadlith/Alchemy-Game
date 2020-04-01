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

    public int getIDValue(){
        return IDValue;
    }

    public String getType(){
        return type;
    }

    public int getQuality(){
        return quality;
    }

    public int getValue(){
        return value;
    }

    public void setIDValue(int nIDValue){
        IDValue = nIDValue;
    }

    public void setType(String nType){
        type = nType;
    }

    public void setQuality(int nQuality){
        quality = nQuality;
    }

    public void setValue(int nValue){
        value = nValue;
    }

}
