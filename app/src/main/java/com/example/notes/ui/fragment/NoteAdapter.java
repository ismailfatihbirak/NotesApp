package com.example.notes.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.data.entity.Notes;
import com.example.notes.databinding.CardTasarimBinding;
import com.example.notes.ui.adapter.AnasayfaFragmentDirections;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.CardTasarimTutucuu>{
    private Context mContext;
    private List<Notes> notesList;

    public NoteAdapter(Context mContext, List<Notes> notesList) {
        this.mContext = mContext;
        this.notesList = notesList;
    }

    public class CardTasarimTutucuu extends RecyclerView.ViewHolder{
        private CardTasarimBinding binding;

        public CardTasarimTutucuu(CardTasarimBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public CardTasarimTutucuu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardTasarimBinding binding =
                CardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new CardTasarimTutucuu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucuu holder, int position) {
        Notes notes = notesList.get(position);
        CardTasarimBinding t = holder.binding;

        t.textViewBaslik.setText(notes.getNotes_baslik());
        t.textViewMetin.setText(notes.getNotes_metin());

        t.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                t.imageViewCheck.setVisibility(View.VISIBLE);
                int color = Color.parseColor("#83EDF1");
                t.cardView.setCardBackgroundColor(color);
                AnasayfaFragmentDirections.ActionBottomsheet gecis = AnasayfaFragmentDirections.actionBottomsheet(notes);
                Navigation.findNavController(v).navigate(gecis);
                return true;
            }
        });

        t.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnasayfaFragmentDirections.ActionAnaGuncelleGecis gecis = AnasayfaFragmentDirections.actionAnaGuncelleGecis(notes);
                Navigation.findNavController(v).navigate(gecis);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
