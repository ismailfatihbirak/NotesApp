package com.example.notes.ui.adapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.notes.R;
import com.example.notes.databinding.FragmentAnasayfaBinding;
import com.example.notes.ui.fragment.NoteAdapter;
import com.example.notes.ui.viewmodel.AnasayfaViewModel;
import com.google.android.material.carousel.CarouselLayoutManager;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnasayfaFragment extends Fragment {
    private FragmentAnasayfaBinding binding;
    private AnasayfaViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);

        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.setSupportActionBar(binding.toolbarAnasayfa);

        binding.rv.setLayoutManager(new GridLayoutManager(requireContext(),2));//*

        viewModel.notesListesi.observe(getViewLifecycleOwner(),notes -> {
            NoteAdapter adapter = new NoteAdapter(requireContext(),notes);//*

            binding.rv.setAdapter(adapter);//*

        });

        binding.fab.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_ana_ekle_gecis);
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {//harf girmeyi bitirip enterı basınca burası çalışıyor
                viewModel.ara(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {//harf girince burası çalışıyor
                viewModel.ara(s);
                return true;
            }
        });



        return binding.getRoot();
    }
    @Override
    public void onResume() {
        super.onResume();
        //viewModel.notlariYukle();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AnasayfaViewModel.class);
        super.onCreate(savedInstanceState);
    }


}