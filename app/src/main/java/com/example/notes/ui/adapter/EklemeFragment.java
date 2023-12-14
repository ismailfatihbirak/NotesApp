package com.example.notes.ui.adapter;


import static com.example.notes.R.id.toolbarEkleme;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.notes.R;
import com.example.notes.databinding.FragmentEklemeBinding;
import com.example.notes.ui.viewmodel.AnasayfaViewModel;
import com.example.notes.ui.viewmodel.EklemeViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EklemeFragment extends Fragment {
    private FragmentEklemeBinding binding;
    private EklemeViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEklemeBinding.inflate(inflater, container, false);

        binding.toolbarEkleme.setTitle("Not ekle");

        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.setSupportActionBar(binding.toolbarEkleme);

        binding.toolbarEkleme.setNavigationIcon(R.drawable.geri_tusu_menu);//burası geri tuşu ekliyor
        binding.toolbarEkleme.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notes_baslik = binding.editTextBaslik.getText().toString();
                String notes_metin = binding.editTextMetin.getText().toString();
                viewModel.ekle(notes_baslik,notes_metin);
                Navigation.findNavController(view).navigate(R.id.action_eklemeFragment_to_anasayfaFragment);
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                String notes_baslik = binding.editTextBaslik.getText().toString();
                String notes_metin = binding.editTextMetin.getText().toString();
                viewModel.ekle(notes_baslik,notes_metin);
                Navigation.findNavController(requireView()).navigate(R.id.action_eklemeFragment_to_anasayfaFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);






        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MenuHost menuHost = (MenuHost) requireActivity();
        menuHost.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
                // Add menu items here
                //menuInflater.inflate(R.menu.toolbar_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                //buraya menu itemlere tıklanınca yapılcak şeyleri yapıyon
                return true;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(EklemeViewModel.class);
        super.onCreate(savedInstanceState);
    }

}