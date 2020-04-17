package com.example.alchemygame;

import com.example.alchemygame.Model.IngredientItem;
import com.example.alchemygame.ui.Inventory.ItemTypes.Ingredient;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void newIngredientValueIsCorrect(){
        IngredientItem testItem = new IngredientItem(5, "alpha", "5", "15");
        assertEquals(testItem.getValue(), "15");
    }

    @Test
    public void newIngredientIDValIsCorrect(){
        IngredientItem testItem = new IngredientItem(5, "alpha", "5", "15");
        assertEquals(testItem.getIDValue(), 5);
    }

    @Test
    public void newIngredientTypeIsCorrect(){
        IngredientItem testItem = new IngredientItem(5, "alpha", "5", "15");
        assertEquals(testItem.getType()
                , "alpha");
    }

    @Test
    public void newIngredientQualityIsCorrect(){
        IngredientItem testItem = new IngredientItem(5, "alpha", "5", "15");
        assertEquals(testItem.getQuality(), "5");
    }
}