package com.example.trafficinfomation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class CctvPlayer extends AppCompatActivity {

    private String rtspUrl = "";
    private ExoPlayer player;
    private StyledPlayerView styledPlayerView;
    private String TAG = "seeVideo";
    private ImageButton fullScreenButton;
    //fullscreen 동작 설정
    boolean isFullScreen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.styled_player_view);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        Log.d(TAG, "onCreate:" + bundle.getString("rtspUrl"));
        rtspUrl = bundle.getString("rtspUrl");

        //player 설정
        player = new ExoPlayer.Builder(CctvPlayer.this).build();
        MediaItem mediaItem = MediaItem.fromUri(rtspUrl);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();

        //styledPlayerView 설정
        styledPlayerView = findViewById(R.id.styledPlayerView);
        styledPlayerView.setPlayer(player);
        styledPlayerView.setKeepScreenOn(true);

        //버튼 설정
        configurePlayButton();
        configureFullScreenButton();

    }

    //play, pause 동작 설정
    private void configurePlayButton() {
        ImageButton playImageButton = findViewById(R.id.playButton);
        playImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.isPlaying()) {
                    player.pause();
                    playImageButton.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    player.play();
                    playImageButton.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });
    }


    private void configureFullScreenButton() {
        fullScreenButton = findViewById(R.id.fullScreen);
        View decorView = getWindow().getDecorView();

        fullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (decorView.getSystemUiVisibility() == (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)) {
                    fullScreenButton.setImageResource(R.drawable.ic_fullscreen_shrink);

                    isFullScreen = true;

                    decorView.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    );

                } else {
                    fullScreenButton.setImageResource(R.drawable.ic_fullscreen_expand);

                    isFullScreen = false;

                    decorView.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    );
                }
            }
        });

        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                if(isFullScreen && i == 0) {
                    fullScreenButton.setImageResource(R.drawable.ic_fullscreen_expand);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }
}
