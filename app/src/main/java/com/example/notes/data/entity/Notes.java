package com.example.notes.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "notes")
public class Notes implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "notes_id")
    @NonNull
    private int notes_id;
    @ColumnInfo(name = "notes_baslik")
    @NonNull
    private String notes_baslik;
    @ColumnInfo(name = "notes_metin")
    @NonNull
    private String notes_metin;

    public Notes() {
    }

    public Notes(int notes_id, String notes_baslik, String notes_metin) {
        this.notes_id = notes_id;
        this.notes_baslik = notes_baslik;
        this.notes_metin = notes_metin;
    }

    public int getNotes_id() {
        return notes_id;
    }

    public void setNotes_id(int notes_id) {
        this.notes_id = notes_id;
    }

    public String getNotes_baslik() {
        return notes_baslik;
    }

    public void setNotes_baslik(String notes_baslik) {
        this.notes_baslik = notes_baslik;
    }

    public String getNotes_metin() {
        return notes_metin;
    }

    public void setNotes_metin(String notes_metin) {
        this.notes_metin = notes_metin;
    }
}
