package micfil.nimor;

import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Main extends AppCompatActivity {

    ImageView cover;
    ImageButton play, pause, prev, next;
    MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.control_play);
        pause = findViewById(R.id.control_pause);
        prev = findViewById(R.id.control_prev);
        next = findViewById(R.id.control_next);

        song = MediaPlayer.create(getApplicationContext(), R.raw.trivium);

    }

    // Play Musid
    public void Play(View v){

        play.setVisibility(View.GONE);
        pause.setVisibility(View.VISIBLE);

        song.start();

    }

    // Pause Musid
    public void Pause(View v){

        pause.setVisibility(View.GONE);
        play.setVisibility(View.VISIBLE);

        song.pause();

    }

}
