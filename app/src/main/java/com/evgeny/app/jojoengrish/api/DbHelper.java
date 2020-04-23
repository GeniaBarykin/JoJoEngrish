package com.evgeny.app.jojoengrish.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.evgeny.app.jojoengrish.models.SoundModel;
import com.evgeny.app.jojoengrish.api.exceptions.NotFoundException;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {


    private final String SQL_DELETE_SOUNDS =
            "DROP TABLE IF EXISTS " + SoundsTableFeeder.TABLE_NAME;
    private final String SQL_DELETE_TAGS =
            "DROP TABLE IF EXISTS " + TagsTableFeeder.TABLE_NAME;

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
        db.execSQL(SQL_DELETE_SOUNDS);
        db.execSQL(SQL_DELETE_TAGS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean postSound(String name, int sound_address, int picture_address, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SoundsTableFeeder.KEY_NAME, name);
        values.put(SoundsTableFeeder.KEY_SOUND_ADDRESS, sound_address);
        values.put(SoundsTableFeeder.KEY_PICTURE_ADDRESS, picture_address);
        values.put(SoundsTableFeeder.KEY_DESCRIPTION, description);

        long newRowId = db.insert(SoundsTableFeeder.TABLE_NAME, null, values);
        db.close();
        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }

    }

    public void reset(){
        SQLiteDatabase db = super.getWritableDatabase();
        db.execSQL(SQL_DELETE_SOUNDS);
        db.execSQL(SQL_DELETE_TAGS);
        onCreate(db);
        SoundsTableFeeder.feed(this);
        try {
            TagsTableFeeder.feed(this);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        db.close();
    }

    public int getSoundAddress(String nameToFind) throws NotFoundException {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + SoundsTableFeeder.KEY_SOUND_ADDRESS + " FROM " + SoundsTableFeeder.TABLE_NAME
                + " WHERE " + SoundsTableFeeder.KEY_NAME + " = '" + nameToFind + "'";
        Cursor data = db.rawQuery(query, null);
        if (data.moveToNext()) {
            int id = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_SOUND_ADDRESS));
            data.close();
            return id;
        }
        data.close();
        throw new NotFoundException("Sound '" + nameToFind + "' was not found");
    }

    public int getSoundId(String nameToFind) throws NotFoundException {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + SoundsTableFeeder.KEY_ID + " FROM " + SoundsTableFeeder.TABLE_NAME
                + " WHERE " + SoundsTableFeeder.KEY_NAME + " = '" + nameToFind + "'";
        Cursor data = db.rawQuery(query, null);
        if (data.moveToNext()) {
            int id = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_ID));
            data.close();
            return id;
        }
        data.close();
        throw new NotFoundException("Sound '" + nameToFind + "' was not found");
    }

    public SoundModel getSound(int idToFind) throws NotFoundException {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + SoundsTableFeeder.TABLE_NAME
                + " WHERE " + SoundsTableFeeder.KEY_ID + " = '" + idToFind + "'";
        Cursor data = db.rawQuery(query, null);
        if (data.moveToNext()) {
            int id = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_ID));
            String name = data.getString(data.getColumnIndex(SoundsTableFeeder.KEY_NAME));
            int sound_adress = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_SOUND_ADDRESS));
            int picture_adress = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_PICTURE_ADDRESS));
            String description = data.getString(data.getColumnIndex(SoundsTableFeeder.KEY_DESCRIPTION));
            data.close();
            return new SoundModel(id, name, sound_adress, picture_adress, description);
        }
        data.close();
        db.close();
        throw new NotFoundException(idToFind + " was not found");
    }

    public ArrayList<SoundModel> getSounds() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + SoundsTableFeeder.TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        ArrayList<SoundModel> sounds = new ArrayList<>();
        while (data.moveToNext()) {
            int id = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_ID));
            String name = data.getString(data.getColumnIndex(SoundsTableFeeder.KEY_NAME));
            int sound_adress = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_SOUND_ADDRESS));
            int picture_adress = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_PICTURE_ADDRESS));
            String description = data.getString(data.getColumnIndex(SoundsTableFeeder.KEY_DESCRIPTION));
            sounds.add(new SoundModel(id, name, sound_adress, picture_adress, description));
        }
        data.close();
        db.close();
        if(countTags()==0){
            try {
                TagsTableFeeder.feed(this);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        for (SoundModel sound :
                sounds) {
                sound.setTags(getTags(sound.getID()));
        }
        return sounds;
    }

//    public int getSoundAddress(int id) throws NotFoundException {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT " + SoundsTableFeeder.KEY_SOUND_ADDRESS + " FROM "
//                + SoundsTableFeeder.TABLE_NAME
//                + " WHERE " + SoundsTableFeeder.KEY_ID + " = '" + id + "'";
//        Cursor data = db.rawQuery(query, null);
//        if (data.moveToNext()) {
//            int sound_adress = data.getInt(data.getColumnIndex(SoundsTableFeeder.KEY_SOUND_ADDRESS));
//
//            data.close();
//            return sound_adress;
//        }
//        data.close();
//        db.close();
//        throw new NotFoundException("Sound '" + id + "' was not found");
//    }


    public long countSounds() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, SoundsTableFeeder.TABLE_NAME);
        db.close();
        return count;
    }

    public boolean postTag(int id, String tag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TagsTableFeeder.KEY_TAG, tag);
        values.put(TagsTableFeeder.KEY_ID, id);
        long newRowId = db.insert(TagsTableFeeder.TABLE_NAME, null, values);
        if (newRowId == -1) {
            return false;
        }
        db.close();
        return true;
    }

    public ArrayList<String> getTags(int soundID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ TagsTableFeeder.KEY_TAG + " FROM " + TagsTableFeeder.TABLE_NAME
                + " WHERE " + TagsTableFeeder.KEY_ID + " = '" + soundID + "'";
        Cursor data = db.rawQuery(query, null);
        ArrayList<String> tags = new ArrayList<>();
        while (data.moveToNext()) {
            tags.add(data.getString(data.getColumnIndex(TagsTableFeeder.KEY_TAG)));
        }
        data.close();
        db.close();
        return tags;
    }

    public ArrayList<String> getAllTags() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ TagsTableFeeder.KEY_TAG + " FROM " + TagsTableFeeder.TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        ArrayList<String> tags = new ArrayList<>();
        while (data.moveToNext()) {
            tags.add(data.getString(data.getColumnIndex(TagsTableFeeder.KEY_TAG)));
        }
        data.close();
        db.close();
        return tags;
    }

    public ArrayList<Integer> getIdByTag(String tagToFind) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT DISTINCT " + TagsTableFeeder.KEY_ID + " FROM " + TagsTableFeeder.TABLE_NAME
                + " WHERE " + TagsTableFeeder.KEY_TAG + " = '" + tagToFind + "'";
        Cursor data = db.rawQuery(query, null);
        ArrayList<Integer> listID = new ArrayList<>();
        while (data.moveToNext()) {
            listID.add(data.getInt(data.getColumnIndex(TagsTableFeeder.KEY_ID)));
        }
        data.close();
        db.close();
        return listID;
    }

    public long countTags() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TagsTableFeeder.TABLE_NAME);
        db.close();
        return count;
    }




}