package com.example.notes.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notes.data.entity.Notes;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM notes")
    Single<List<Notes>> notlariYukle();

    @Insert
    Completable ekle(Notes not);

    @Update
    Completable guncelle(Notes not);
    @Delete
    Completable sil(Notes not);
    @Query("SELECT * FROM notes WHERE notes_metin like '%' || :aramaKelimesi || '%' OR notes_baslik LIKE '%' || :aramaKelimesi || '%'")//bu sorgu veritabanında filtreleme
        //yapıyor ona göre veri getiriyor
    Single<List<Notes>> ara(String aramaKelimesi);
}
