package com.example.notes.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.notes.data.entity.Notes;

@Database(entities = {Notes.class},version = 1)
public abstract class Veritabani extends RoomDatabase {
    public abstract NotesDao getNotesDao();
}
