package com.evgeny.app.jojoengrish.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.evgeny.app.jojoengrish.models.GroupModel;
import com.evgeny.app.jojoengrish.models.SoundModel;
import com.evgeny.app.jojoengrish.api.exceptions.NotFoundException;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static DbHelper getDbHelper() {
        return dbHelper;
    }

    private static DbHelper dbHelper;

    private final String SQL_DELETE_SOUNDS =
            "DROP TABLE IF EXISTS " + SoundsTableFeeder.TABLE_NAME;
    private final String SQL_DELETE_TAGS =
            "DROP TABLE IF EXISTS " + TagsTableFeeder.TABLE_NAME;
    private final String SQL_DELETE_GROUPS =
            "DROP TABLE IF EXISTS " + GroupsTableFeeder.TABLE_NAME;
    private final String SQL_DELETE_GROUPS_SOUNDS =
            "DROP TABLE IF EXISTS " + GroupsSoundsTableFeeder.TABLE_NAME;

    public DbHelper(@Nullable Context context) {
        super(context, SoundsTableFeeder.TABLE_NAME, null, 1);
        dbHelper = this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SoundsTableFeeder.makeContract());
        db.execSQL(TagsTableFeeder.makeContract());
        db.execSQL(GroupsTableFeeder.makeContract());
        db.execSQL(GroupsSoundsTableFeeder.makeContract());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_SOUNDS);
        db.execSQL(SQL_DELETE_TAGS);
        db.execSQL(SQL_DELETE_GROUPS);
        db.execSQL(SQL_DELETE_GROUPS_SOUNDS);
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
        db.execSQL(SQL_DELETE_GROUPS);
        db.execSQL(SQL_DELETE_GROUPS_SOUNDS);
        onCreate(db);
        SoundsTableFeeder.feed(this);
        GroupsTableFeeder.feed(this);
        GroupsSoundsTableFeeder.feed(this);
        try {
            TagsTableFeeder.feed(this);
        } catch (NotFoundException e) {
            reset();
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
        return sounds;
    }



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
                + " WHERE " + TagsTableFeeder.KEY_TAG + " LIKE '%" + tagToFind + "%'";
        Cursor data = db.rawQuery(query, null);
        ArrayList<Integer> listID = new ArrayList<>();
        while (data.moveToNext()) {
            listID.add(data.getInt(data.getColumnIndex(TagsTableFeeder.KEY_ID)));
        }
        data.close();
        db.close();
        return listID;
    }

    public ArrayList<SoundModel> getSoundIDsByTagASC(String tagToFind) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="";
        if(tagToFind.isEmpty()){
            query = "SELECT * FROM " + SoundsTableFeeder.TABLE_NAME
            + " ORDER BY " + SoundsTableFeeder.KEY_NAME + " ASC";
        } else {
            query = "SELECT DISTINCT * FROM " + SoundsTableFeeder.TABLE_NAME
                    + " INNER JOIN " + TagsTableFeeder.TABLE_NAME + " on "
                    + SoundsTableFeeder.TABLE_NAME + "."+ SoundsTableFeeder.KEY_ID + " = "
                    + TagsTableFeeder.TABLE_NAME + "." +TagsTableFeeder.KEY_ID
                    + " WHERE " + TagsTableFeeder.KEY_TAG + " LIKE '%" + tagToFind + "%'"
                    + " ORDER BY " + SoundsTableFeeder.KEY_NAME + " ASC";
        }
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
        return sounds;
    }

    public long countTags() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TagsTableFeeder.TABLE_NAME);
        db.close();
        return count;
    }

    public boolean postGroupSound (String name, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroupsSoundsTableFeeder.KEY_NAME, name);
        values.put(GroupsSoundsTableFeeder.KEY_SOUND_ID, id);
        long newRowId = db.insert(GroupsSoundsTableFeeder.TABLE_NAME, null, values);
        if (newRowId == -1) {
            return false;
        }
        db.close();
        return true;
    }

    public boolean postGroup (String name, int picture){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroupsTableFeeder.KEY_NAME, name);
        values.put(GroupsTableFeeder.KEY_PICTURE_ADDRESS, picture);
        long newRowId = db.insert(GroupsTableFeeder.TABLE_NAME, null, values);
        if (newRowId == -1) {
            return false;
        }
        db.close();
        return true;
    }

    public ArrayList<SoundModel> getSoundsFromGroup(String groupName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + SoundsTableFeeder.TABLE_NAME
                + " INNER JOIN " + GroupsSoundsTableFeeder.TABLE_NAME + " on "
                + SoundsTableFeeder.TABLE_NAME + "."+ SoundsTableFeeder.KEY_ID + " = "
                + GroupsSoundsTableFeeder.TABLE_NAME + "." + GroupsSoundsTableFeeder.KEY_SOUND_ID
                + " WHERE " + GroupsSoundsTableFeeder.TABLE_NAME + "." + GroupsSoundsTableFeeder.KEY_NAME + " LIKE '%" + groupName + "%'"
                + " ORDER BY " + SoundsTableFeeder.TABLE_NAME + "." + SoundsTableFeeder.KEY_NAME + " ASC";
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
        return sounds;
    }

    public ArrayList<GroupModel> getGroups(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + GroupsTableFeeder.TABLE_NAME
                + " ORDER BY " + GroupsTableFeeder.KEY_NAME + " ASC";
        Cursor data = db.rawQuery(query, null);

        ArrayList<GroupModel> groups = new ArrayList<>();
        while (data.moveToNext()) {
            String name = data.getString(data.getColumnIndex(GroupsTableFeeder.KEY_NAME));
            int picture = data.getInt(data.getColumnIndex(GroupsTableFeeder.KEY_PICTURE_ADDRESS));
            groups.add(new GroupModel(name,picture));
        }
        data.close();
        db.close();
        for (GroupModel group :
                groups) {
            group.addSounds(getSoundsFromGroup(group.getName()));
        }
        return groups;
    }



}