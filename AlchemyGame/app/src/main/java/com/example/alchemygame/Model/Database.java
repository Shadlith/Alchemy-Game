package com.example.alchemygame.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME = "AlchemyGame.db";
    private static final String Player_table = "Player";
    private static final String Location_table = "Location";
    private static final String Perks_table = "Perks";
    private static final String Inventory_table = "Inventory";
    private static final String Potions_table = "Potions";
    private static final String Ingredients_table = "Ingredients";



    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String player = "CREATE TABLE " + Player_table + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, XP INTEGER, Buffs TEXT)";
        String location = "CREATE TABLE " + Location_table + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, Lang REAL, Long REAL)";
        String perks = "CREATE TABLE " + Perks_table + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Effect TEXT)";
        String inventory = "CREATE TABLE " + Inventory_table + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, Capacity INTEGER)";
        String potions = "CREATE TABLE " + Potions_table + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT, Effect TEXT, Recipe TEXT)";
        String ingredients = "CREATE TABLE " + Ingredients_table + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT, Quality TEXT)";

        db.execSQL(player);
        db.execSQL(location);
        db.execSQL(perks);
        db.execSQL(inventory);
        db.execSQL(potions);
        db.execSQL(ingredients);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Player_table);
        db.execSQL("DROP TABLE IF EXISTS " + Location_table);
        db.execSQL("DROP TABLE IF EXISTS " + Perks_table);
        db.execSQL("DROP TABLE IF EXISTS " + Inventory_table);
        db.execSQL("DROP TABLE IF EXISTS " + Potions_table);
        db.execSQL("DROP TABLE IF EXISTS " + Ingredients_table);
        onCreate(db);
    }

    public boolean addLocation(double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Lang", latitude);
        values.put("Long", longitude);

        db.insert(Location_table, null, values);
        return true;
    }

    public boolean addPlayer(int xp, String buff) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("XP", xp);
        values.put("Buffs", buff);

        db.insert(Player_table, null, values);
        return true;
    }

    public boolean addPerks(String name, String effect) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Effect", effect);

        db.insert(Perks_table, null, values);
        return true;
    }

    public boolean addPotions(String type, String effect, String recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Type", type);
        values.put("Effect", effect);
        values.put("Recipe", recipe);

        db.insert(Potions_table, null, values);
        return true;
    }

    public boolean addInventory(int cap) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Capacity", cap);

        db.insert(Inventory_table, null, values);
        return true;
    }

    public boolean addIngredients(String type, String quality) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Type", type);
        values.put("Quality", quality);

        db.insert(Perks_table, null, values);
        return true;
    }


}