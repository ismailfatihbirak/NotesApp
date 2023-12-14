package com.example.notes.ui.adapter;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.notes.data.entity.Notes;
import com.example.notes.databinding.FragmentEklemeBinding;
import com.example.notes.databinding.FragmentGuncelleBinding;
import com.example.notes.ui.viewmodel.AnasayfaViewModel;
import com.example.notes.ui.viewmodel.GuncelleViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GuncelleFragment extends Fragment {
    private FragmentGuncelleBinding binding;
    private GuncelleViewModel viewModel;
    private Notes gelenNote;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGuncelleBinding.inflate(inflater, container, false);

        binding.toolbarGuncelle.setTitle("Güncelle");

        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.setSupportActionBar(binding.toolbarGuncelle);

        GuncelleFragmentArgs bundle = GuncelleFragmentArgs.fromBundle(getArguments());
        gelenNote = bundle.getNot();

        binding.editTextGuncelleBaslik.setText(gelenNote.getNotes_baslik());
        binding.editTextGuncelleMetin.setText(gelenNote.getNotes_metin());

        binding.toolbarGuncelle.setNavigationIcon(R.drawable.geri_tusu_menu);//burası geri tuşu ekliyor
        binding.toolbarGuncelle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notes_baslik = binding.editTextGuncelleBaslik.getText().toString();
                String notes_metin = binding.editTextGuncelleMetin.getText().toString();
                viewModel.guncelle(gelenNote.getNotes_id(),notes_baslik,notes_metin);
                Navigation.findNavController(view).navigate(R.id.action_guncelleFragment_to_anasayfaFragment);
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                String notes_baslik = binding.editTextGuncelleBaslik.getText().toString();
                String notes_metin = binding.editTextGuncelleMetin.getText().toString();
                viewModel.guncelle(gelenNote.getNotes_id(),notes_baslik,notes_metin);
                Navigation.findNavController(requireView()).navigate(R.id.action_guncelleFragment_to_anasayfaFragment);
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
                menuInflater.inflate(R.menu.toolbar_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                //buraya menu itemlere tıklanınca yapılcak şeyleri yapıyon
                viewModel.sil(gelenNote.getNotes_id());
                Navigation.findNavController(requireView()).navigate(R.id.action_guncelleFragment_to_anasayfaFragment);
                return true;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(GuncelleViewModel.class);
        super.onCreate(savedInstanceState);
    }
}