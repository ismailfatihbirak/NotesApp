package com.example.notes.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.notes.data.repo.NotesDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class BottomSheetViewModel extends ViewModel {

    NotesDaoRepository krepo;
    @Inject
    public BottomSheetViewModel(NotesDaoRepository krepo){
        this.krepo=krepo;
    }
    public void sil(int notes_id){
        krepo.sil(notes_id);
    }
}
