package com.example.notes.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.notes.data.entity.Notes;
import com.example.notes.data.repo.NotesDaoRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfaViewModel extends ViewModel {
    NotesDaoRepository krepo;
    public MutableLiveData<List<Notes>> notesListesi = new MutableLiveData<>();
    @Inject
    public AnasayfaViewModel(NotesDaoRepository krepo){
        this.krepo=krepo;
        notlariYukle();
        notesListesi = krepo.notesListesi;

    }

    public void notlariYukle(){
        krepo.notlariYukle();
    }

    public void ara(String aramaKelimesi){krepo.ara(aramaKelimesi);}
}
