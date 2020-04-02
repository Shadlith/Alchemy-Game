package com.example.alchemygame.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        String ingredients = "CREATE TABLE " + Ingredients_table + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, Type TEXT, Quality TEXT, Value TEXT)";

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
        Log.v("Database", "Added Location");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Lang", latitude);
        values.put("Long", longitude);

        db.insert(Location_table, null, values);
        db.close();
        return true;
    }

    public Map<Integer, Location> getLocations() {
        Log.v("Database", "Getting Location");
        SQLiteDatabase db = this.getReadableDatabase();
        Map<Integer, Location> array_list = new HashMap<Integer, Location>();

        Cursor res = db.rawQuery("SELECT * FROM Location", null);
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            Location loc = new Location("");
            loc.setLatitude(Double.parseDouble(res.getString(res.getColumnIndex("Lang"))));
            loc.setLongitude(Double.parseDouble(res.getString(res.getColumnIndex("Long"))));

            array_list.put(res.getInt(0), loc);
            res.moveToNext();
        }
        db.close();
        return array_list;
    }

    public boolean deleteLocation(int ID) {
        Log.v("Database", "Deleted Location");
        SQLiteDatabase db = this.getWritableDatabase();
        String where="ID=";
        db.execSQL("DELETE FROM Location WHERE ID= " + ID);

        return true;
    }

    public boolean addPlayer(int xp, String buff) {
        Log.v("Database", "Added Player");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("XP", xp);
        values.put("Buffs", buff);

        db.insert(Player_table, null, values);
        return true;
    }

    public boolean addPerks(String name, String effect) {
        Log.v("Database", "Added Perks");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Effect", effect);

        db.insert(Perks_table, null, values);
        return true;
    }

    public boolean addPotions(String type, String effect, String recipe) {
        Log.v("Database", "Added Potions");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Type", type);
        values.put("Effect", effect);
        values.put("Recipe", recipe);

        db.insert(Potions_table, null, values);
        return true;
    }

    public boolean addInventory(int cap) {
        Log.v("Database", "Added Inventory");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Capacity", cap);

        db.insert(Inventory_table, null, values);
        return true;
    }

    public boolean addIngredients(String type, String quality, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Type", type);
        values.put("Quality", quality);
        values.put("Value", value);

        db.insert(Ingredients_table, null, values);
        return true;
    }

    public ArrayList<IngredientItem> getIngredients() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<IngredientItem> array_list = new ArrayList<IngredientItem>();

        Cursor res = db.rawQuery("SELECT * FROM Ingredients", null);
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            IngredientItem temp = new IngredientItem(
                res.getInt(0),
                res.getString(1),
                res.getString(2),
                res.getString(3)
            );

            array_list.add(temp);
            res.moveToNext();
        }
        return array_list;
    }

}
