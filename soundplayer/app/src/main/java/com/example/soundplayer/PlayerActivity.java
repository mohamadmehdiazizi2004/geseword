package com.example.soundplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soundplayer.databinding.ActivityPlayerBinding;

import java.util.List;
import java.util.Locale;

public class PlayerActivity extends AppCompatActivity {

    private ActivityPlayerBinding b;

    private List<SongEntity> songs;
    private int pos = 0;

    private MediaPlayer player;
    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            if (player != null) {
                int cur = player.getCurrentPosition();
                b.seekBar.setProgress(cur);
                b.txtCurrent.setText(formatTime(cur));
            }
            handler.postDelayed(this, 250);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        songs = AppDatabase.getInstance(this).songDao().getAll();
        pos = getIntent().getIntExtra("pos", 0);

        b.btnBack.setOnClickListener(v -> finish());

        b.btnPrev.setOnClickListener(v -> playAt(Math.max(0, pos - 1)));
        b.btnNext.setOnClickListener(v -> playAt(Math.min(songs.size() - 1, pos + 1)));

        b.btnPlayPause.setOnClickListener(v -> {
            if (player == null) return;
            if (player.isPlaying()) {
                player.pause();
                updatePlayIcon(false);
            } else {
                player.start();
                updatePlayIcon(true);
            }
        });

        b.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && player != null) b.txtCurrent.setText(formatTime(progress));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {
                if (player != null) player.seekTo(seekBar.getProgress());
            }
        });

        playAt(pos);
        handler.post(progressRunnable);
    }

    private void playAt(int newPos) {
        pos = newPos;
        SongEntity s = songs.get(pos);

        b.txtTitle.setText(s.title);
        b.txtArtist.setText(s.artist);
        b.imgCoverLarge.setImageResource(s.coverResId);

        releasePlayer();

        player = MediaPlayer.create(this, s.audioResId);
        if (player == null) return;

        b.seekBar.setMax(player.getDuration());
        b.txtTotal.setText(formatTime(player.getDuration()));
        b.seekBar.setProgress(0);
        b.txtCurrent.setText(formatTime(0));

        player.setOnCompletionListener(mp -> {
            if (pos < songs.size() - 1) playAt(pos + 1);
            else updatePlayIcon(false);
        });

        player.start();
        updatePlayIcon(true);
    }

    private void updatePlayIcon(boolean playing) {
        b.btnPlayPause.setImageResource(playing ? R.drawable.ic_pause : R.drawable.ic_play);
    }

    private String formatTime(int ms) {
        int totalSec = ms / 1000;
        int m = totalSec / 60;
        int s = totalSec % 60;
        return String.format(Locale.US, "%02d:%02d", m, s);
    }

    private void releasePlayer() {
        if (player != null) {
            try { player.stop(); } catch (Exception ignored) {}
            player.release();
            player = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(progressRunnable);
        releasePlayer();
    }
}
