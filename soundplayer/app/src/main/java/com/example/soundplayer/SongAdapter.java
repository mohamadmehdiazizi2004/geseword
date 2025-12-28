package com.example.soundplayer;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soundplayer.databinding.ItemSongBinding;

import java.util.List;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.VH> {

    public interface OnSongClick {
        void onClick(SongEntity song, int position);
    }

    private final List<SongEntity> songs;
    private final OnSongClick listener;

    public SongAdapter(List<SongEntity> songs, OnSongClick listener) {
        this.songs = songs;
        this.listener = listener;
    }

    static class VH extends RecyclerView.ViewHolder {
        ItemSongBinding b;
        VH(ItemSongBinding b) {
            super(b.getRoot());
            this.b = b;
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSongBinding b = ItemSongBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new VH(b);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        SongEntity s = songs.get(position);

        holder.b.txtTitle.setText(s.title);
        holder.b.txtArtist.setText(s.artist);
        holder.b.imgCover.setImageResource(s.coverResId);

        holder.b.btnPlay.setOnClickListener(v -> listener.onClick(s, position));
        holder.b.getRoot().setOnClickListener(v -> listener.onClick(s, position));
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
