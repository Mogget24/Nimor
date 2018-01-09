package micfil.nimor;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    TextView title;
    ImageView cover;
    ImageButton play, pause, prev, next;
    MediaPlayer song;
    Typeface MetalMacabre;
    SeekBar seekBar;
    Handler handler;
    Runnable runnable;
    Button toggleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup font
        MetalMacabre = Typeface.createFromAsset(getAssets(),  "fonts/MetalMacabre.ttf");

        // Setup controls
        play = findViewById(R.id.control_play);
        pause = findViewById(R.id.control_pause);
        prev = findViewById(R.id.control_prev);
        next = findViewById(R.id.control_next);

        // Load custom song
        song = MediaPlayer.create(getApplicationContext(), R.raw.trivium);
        song.setAudioStreamType(AudioManager.STREAM_MUSIC);

        // Setup title
        title = findViewById(R.id.song_title);
        title.setTypeface(MetalMacabre);

        // Seekbar

        handler = new Handler();
        seekBar = findViewById(R.id.seekbar);
        seekBar.setMax(song.getDuration());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if(input){
                    song.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    // Play Music
    public void Play(View v){

        play.setVisibility(View.GONE);
        pause.setVisibility(View.VISIBLE);

        // Play song and handle seekbar
        song.start();
        playCycle();

    }

    // Pause Music
    public void Pause(View v){

        pause.setVisibility(View.GONE);
        play.setVisibility(View.VISIBLE);

        song.pause();

    }

    // Seekbar handling
    public void playCycle(){
        seekBar.setProgress(song.getCurrentPosition());

        if(song.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        song.release();
        handler.removeCallbacks(runnable);
    }

    // Show Menu
    public void showMenu(View v){

        Log.d("ou", "ou");

        Intent intent = new Intent (this, Menu.class);
        startActivity(intent);

    }
}