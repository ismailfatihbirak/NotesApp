package com.example.notes.di;

import android.content.Context;

import androidx.room.Room;

import com.example.notes.data.repo.NotesDaoRepository;
import com.example.notes.room.NotesDao;
import com.example.notes.room.Veritabani;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public NotesDaoRepository provideNotesDaoRepository(NotesDao kdao){
         return new NotesDaoRepository(kdao);
    }

    @Provides
    @Singleton
    public NotesDao provideNotesDao(@ApplicationContext Context context){
        Veritabani vt = Room.databaseBuilder(context, Veritabani.class,"notes.sqlite")
                .createFromAsset("notes.sqlite").build();
        return vt.getNotesDao();
    }

}
