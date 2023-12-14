package com.example.notes.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.notes.data.entity.Notes;
import com.example.notes.room.NotesDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NotesDaoRepository {
    public MutableLiveData<List<Notes>> notesListesi = new MutableLiveData<>();

    private NotesDao kdao;
    public NotesDaoRepository(NotesDao kdao){
        this.kdao=kdao;
    }
    public void notlariYukle(){
        kdao.notlariYukle().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Notes>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Notes> notes) {
                                notesListesi.setValue(notes);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });


    }
    public void ekle(String notes_baslik, String notes_metin) {
        if (notes_metin == null || notes_metin.trim().isEmpty()) {
            // Not metni boş veya sadece boşluklardan oluşuyorsa işlemi yapma
            return;
        }

        Notes not = new Notes(0, notes_baslik, notes_metin);
        kdao.ekle(not)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Abonelik işlemleri
                    }

                    @Override
                    public void onComplete() {
                        // İşlem tamamlandığında yapılacaklar
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Hata durumunda yapılacaklar
                    }
                });
    }

    public void guncelle(int notes_id,String notes_baslik,String notes_metin){
        Notes not = new Notes(notes_id,notes_baslik,notes_metin);
        kdao.guncelle(not).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void sil(int notes_id){
        //notlariYukle();

        Notes not = new Notes(notes_id,"","");
        kdao.sil(not).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onComplete() {
                        notlariYukle();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    public void ara(String aramaKelimesi){
        kdao.ara(aramaKelimesi).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Notes>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Notes> kisilers) {
                        notesListesi.setValue(kisilers);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
