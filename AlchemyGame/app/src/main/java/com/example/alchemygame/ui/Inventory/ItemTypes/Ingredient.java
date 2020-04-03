package com.example.alchemygame.ui.Inventory.ItemTypes;

import java.util.Random;

public class Ingredient {

    public String Value;
    public String Type;
    public String Quality;

    //Call constructor and it'll randomly generate an ingredient
    public Ingredient(){
        Random rand = new Random();
        int Value = 0;
        int odds = rand.nextInt(3);
        if( odds == 0){
            Type = "Herbs";
            Value =+ 50;
        }else if(odds == 1){
            Type = "Ore";
            Value =+ 70;
        }else{
            Type = "Wood";
            Value =+ 20;
        }

        odds = rand.nextInt(3);
        if( odds == 0){
            Quality = "Poor";
            Value =+ 50;
        }else if(odds == 1){
            Quality = "Fine";
            Value =+ 100;
        }else{
            Quality = "Great";
            Value =+ 200;
        }

        this.Value = String.valueOf(Value);
    }
}
