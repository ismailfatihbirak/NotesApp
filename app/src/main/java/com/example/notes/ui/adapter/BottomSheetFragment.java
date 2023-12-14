package com.example.notes.ui.adapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notes.R;
import com.example.notes.data.entity.Notes;
import com.example.notes.databinding.FragmentBottomSheetBinding;
import com.example.notes.ui.viewmodel.AnasayfaViewModel;
import com.example.notes.ui.viewmodel.BottomSheetViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BottomSheetFragment extends BottomSheetDialogFragment {
    private FragmentBottomSheetBinding binding;
    private BottomSheetViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false);


        binding.imageViewSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragmentArgs bundle = BottomSheetFragmentArgs.fromBundle(getArguments());
                Notes gelenNote = bundle.getNot();
                viewModel.sil(gelenNote.getNotes_id());
                dismiss();

            }
        });

        return binding.getRoot();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BottomSheetViewModel.class);
        super.onCreate(savedInstanceState);
    }
}