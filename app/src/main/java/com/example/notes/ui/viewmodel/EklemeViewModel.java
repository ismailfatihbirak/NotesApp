package com.example.notes.ui.viewmodel;



import androidx.lifecycle.ViewModel;

import com.example.notes.data.repo.NotesDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EklemeViewModel extends ViewModel {

    NotesDaoRepository krepo;
    @Inject
    public EklemeViewModel(NotesDaoRepository krepo){
        this.krepo=krepo;
    }
    public void ekle(String notes_baslik,String notes_metin){
        krepo.ekle(notes_baslik,notes_metin);
    }
}
