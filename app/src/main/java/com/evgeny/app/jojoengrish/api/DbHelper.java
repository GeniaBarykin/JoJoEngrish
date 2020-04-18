package com.evgeny.app.jojoengrish.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.evgeny.app.jojoengrish.models.SoundModel;

public abstract class DbHelper extends SQLiteOpenHelper {

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Table1Feeder.TABLE_NAME;

    public DbHelper(@Nullable Context context) {
        super(context,Table1Feeder.TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table1Feeder.makeContract());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }

    public boolean post(SoundModel sound){
        SQLiteDatabase db = this.getWritableDatabase();
// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Table1Feeder.COLUMN_2, sound.getName());
        values.put(Table1Feeder.COLUMN_3, sound.getSound_adress());
        values.put(Table1Feeder.COLUMN_4, sound.getPicture_adress());
        values.put(Table1Feeder.COLUMN_5, sound.getDescription());

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Table1Feeder.TABLE_NAME, null, values);
        if(newRowId == -1) {
            return false;
        } else {
            return true;
        }
    }
}