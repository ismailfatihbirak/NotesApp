package com.example.notes.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.notes.data.repo.NotesDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GuncelleViewModel extends ViewModel {

    NotesDaoRepository krepo;
    @Inject
    public GuncelleViewModel(NotesDaoRepository krepo){
        this.krepo=krepo;
    }
    public void guncelle(int notes_id,String notes_baslik,String notes_metin){
        krepo.guncelle(notes_id,notes_baslik,notes_metin);
    }
    public void sil(int notes_id){
        krepo.sil(notes_id);
    }
}
