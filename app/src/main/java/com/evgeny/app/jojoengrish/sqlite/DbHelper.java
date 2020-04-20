package com.evgeny.app.jojoengrish.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.evgeny.app.jojoengrish.models.SoundModel;
import com.evgeny.app.jojoengrish.sqlite.exceptions.NotFoundException;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {


    private final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SoundsTableFeeder.TABLE_NAME;

    public DbHelper(@Nullable Context context) {
        super(context, SoundsTableFeeder.TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SoundsTableFeeder.makeContract());
        db.execSQL(TagsTableFeeder.makeContract());


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean postSound(String name, int sound_address, int picture_address, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SoundsTableFeeder.KEY_NAME, name);
        values.put(SoundsTableFeeder.KEY_SOUND_ADDRESS, sound_address);
        values.put(SoundsTableFeeder.KEY_PICTURE_ADDRESS, picture_address);
        values.put(SoundsTableFeeder.KEY_DESCRIPTION, description);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(SoundsTableFeeder.TABLE_NAME, null, values);
        db.close();
        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }

    }

    public int getSoundId(String nameToFind) throws NotFoundException{
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + SoundsTableFeeder.KEY_ID + " FROM " + SoundsTableFeeder.TABLE_NAME
                + " WHERE "+ SoundsTableFeeder.KEY_NAME +" = '" + nameToFind + "'";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToNext()) {
            int id = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_ID));
            data.close();
            return id;
        }
        data.close();
        throw new NotFoundException(nameToFind + " was not found");
    }

    public SoundModel getSound(String nameToFind) throws NotFoundException{
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + SoundsTableFeeder.TABLE_NAME
                + " WHERE "+ SoundsTableFeeder.KEY_NAME +" = '" + nameToFind + "'";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToNext()) {
            int id = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_ID));
            String name = data.getString(data.getColumnIndex(SoundsTableFeeder.KEY_NAME));
            int sound_adress = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_SOUND_ADDRESS));
            int picture_adress = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_PICTURE_ADDRESS));
            String description = data.getString(data.getColumnIndex(SoundsTableFeeder.KEY_DESCRIPTION));
            data.close();
            return new SoundModel(id, name, sound_adress, picture_adress, description);
        }
        data.close();
        throw new NotFoundException(nameToFind + " was not found");
    }

    public boolean postTag(int id, String tag){
        SQLiteDatabase db = this.getWritableDatabase();
// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TagsTableFeeder.KEY_TAG, tag);
        values.put(TagsTableFeeder.KEY_ID, id);
        long newRowId = db.insert(TagsTableFeeder.TABLE_NAME, null, values);
        if(newRowId == -1) {
            return false;
        }
        db.close();
        return true;
    }

    public ArrayList<Integer> getIdByTag(String tagToFind){
        SQLiteDatabase db = this.getWritableDatabase();
        String query  = "SELECT " + TagsTableFeeder.KEY_ID + " FROM " + TagsTableFeeder.TABLE_NAME
                + " WHERE "+ TagsTableFeeder.KEY_TAG +" = '" + tagToFind + "'";
        Cursor data = db.rawQuery(query,null);
        ArrayList<Integer> listID = new ArrayList<>();
        while(data.moveToNext()){
            listID.add(data.getInt(data.getColumnIndex(TagsTableFeeder.KEY_ID)));
        }
        data.close();
        return  listID;
    }
}