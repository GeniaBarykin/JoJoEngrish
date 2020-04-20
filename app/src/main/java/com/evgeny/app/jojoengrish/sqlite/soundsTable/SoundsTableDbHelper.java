package com.evgeny.app.jojoengrish.sqlite.soundsTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.evgeny.app.jojoengrish.models.SoundModel;

import java.util.ArrayList;

public class SoundsTableDbHelper extends SQLiteOpenHelper {


    private final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Table1Feeder.TABLE_NAME;

    public SoundsTableDbHelper(@Nullable Context context) {
        super(context, Table1Feeder.TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table1Feeder.makeContract());

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

    public boolean post(String name, int sound_address, int picture_address, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Table1Feeder.KEY_NAME, name);
        values.put(Table1Feeder.KEY_SOUND_ADDRESS, sound_address);
        values.put(Table1Feeder.KEY_PICTURE_ADRESS, picture_address);
        values.put(Table1Feeder.KEY_DESCRIPTION, description);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Table1Feeder.TABLE_NAME, null, values);
        db.close();
        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }

    }

    public SoundModel get(String nameToFind) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table1Feeder.TABLE_NAME + " WHERE name = '" + nameToFind + "'";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToNext()) {
            int id = data.getInt(data.getColumnIndex(Table1Feeder.KEY_ID));
            String name = data.getString(data.getColumnIndex(Table1Feeder.KEY_NAME));
            int sound_adress = data.getInt(data.getColumnIndex(Table1Feeder.KEY_SOUND_ADDRESS));
            int picture_adress = data.getInt(data.getColumnIndex(Table1Feeder.KEY_PICTURE_ADRESS));
            String description = data.getString(data.getColumnIndex(Table1Feeder.KEY_DESCRIPTION));
            data.close();
            SoundModel sm = new SoundModel(id, name, sound_adress, picture_adress, description);
            return sm;
        }
        return null;
    }
}