package com.example.alchemygame.ui.Inventory.ItemTypes;

import com.example.alchemygame.Model.IngredientItem;

import java.util.HashMap;

public class Potion {

    private int id;
    private String Type;
    private String Effect;
    private String Recipe;

    public Potion(){
        this.Type = "";
        this.Effect = "";
        this.Recipe = null;
    }

    public Potion(int id, String Type, String Effect, String Recipe){
        this.id = id;
        this.Type = Type;
        this.Effect = Effect;
        this.Recipe = Recipe;

    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getEffect() {
        return Effect;
    }

    public void setEffect(String effect) {
        Effect = effect;
    }

}
