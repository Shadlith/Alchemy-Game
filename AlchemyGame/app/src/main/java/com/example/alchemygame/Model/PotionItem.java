package com.example.alchemygame.Model;

public class PotionItem {
    private int IDValue;
    private String type;
    private int quality;
    private int value;
    private int[] recipe;

    public PotionItem(){
        IDValue = 0;
        type = "Placeholder";
        quality = 0;
        value = 0;
        recipe = new int[]{ 0, 0, 0};
    }

    public PotionItem(int nIDVal, String nType, int nQual, int nVal, int ingredient1, int ingredient2, int ingredient3){
        IDValue = nIDVal;
        type = nType;
        quality = nQual;
        value = nVal;
        recipe = new int[]{ingredient1, ingredient2, ingredient3};
    }

    public String getType(){
        return type;
    }
}
