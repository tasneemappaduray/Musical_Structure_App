package com.example.android.musicalstructureapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;;
import android.widget.TextView;



public class MusicalStructureApp extends AppCompatActivity {

//    public TextView songName, duration;
//    private MediaPlayer mediaPlayer;
//    private double timeElapsed = 0, finalTime = 0;
//    private int forwardTime = 10000, backwardTime = 2000;
//    private Handler durationHandler = new Handler();
//    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musical_structure_app);


        /** Find the View that shows the rock, love, flm and african category **/
        TextView rock = findViewById(R.id.rock);
        TextView love = findViewById(R.id.love);
        TextView film = findViewById(R.id.film);
        TextView african = findViewById(R.id.african);

        /** Set a click listener on the rock View **/
        rock.setOnClickListener(new View.OnClickListener() {
            /** The code in this method will be executed when the rock View is clicked on.*/
            @Override
            public void onClick(View view) {
                Intent rockIntent = new Intent(MusicalStructureApp.this, RockActivity.class);
                startActivity(rockIntent);
            }
        });

        /** Set a click listener on the love View **/
        love.setOnClickListener(new View.OnClickListener() {
            /** The code in this method will be executed when the love View is clicked on. **/
            @Override
            public void onClick(View view) {
                Intent loveIntent = new Intent(MusicalStructureApp.this, LoveActivity.class);
                startActivity(loveIntent);
            }
        });

        /** Set a click listener on the film View **/
        film.setOnClickListener(new View.OnClickListener() {
            /** The code in this method will be executed when the film View is clicked on. **/
            @Override
            public void onClick(View view) {
                Intent filmIntent = new Intent(MusicalStructureApp.this, FilmActivity.class);
                startActivity(filmIntent);
            }
        });

        /** Set a click listener on the african View **/
        african.setOnClickListener(new View.OnClickListener() {
            /** The code in this method will be executed when the africans View is clicked on. **/
            @Override
            public void onClick(View view) {
                Intent africanIntent = new Intent(MusicalStructureApp.this, AfricanActivity.class);
                startActivity(africanIntent);
            }
        });

        //initializeViews();
    }

//    public void initializeViews() {
//        songName = (TextView) findViewById(R.id.songName);
//        mediaPlayer = MediaPlayer.create(this, R.raw.the_cranberries_zombie);
//        finalTime = mediaPlayer.getDuration();
//        duration = (TextView) findViewById(R.id.songDuration);
//        seekbar = (SeekBar) findViewById(R.id.seekBar);
//        songName.setText("Sample_Song.mp3");
//        seekbar.setMax((int) finalTime);
//        seekbar.setClickable(false);
//    }
//
//    // play mp3 song
//    public void play(View view) {
//        mediaPlayer.start();
//        timeElapsed = mediaPlayer.getCurrentPosition();
//        seekbar.setProgress((int) timeElapsed);
//        durationHandler.postDelayed(updateSeekBarTime, 100);
//    }
//
//    //handler to change seekBarTime
//    private Runnable updateSeekBarTime = new Runnable() {
//        public void run() {
//            //get current position
//            timeElapsed = mediaPlayer.getCurrentPosition();
//            //set seekbar progress
//            seekbar.setProgress((int) timeElapsed);
//            //set time remaining
//            double timeRemaining = finalTime - timeElapsed;
//            duration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining), TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));
//            //repeat yourself that again in 100 miliseconds
//            durationHandler.postDelayed(this, 100);
//        }
//    };
//
//    // pause mp3 song
//    public void pause(View view) {
//        mediaPlayer.pause();
//    }
//
//    // go forward at forwardTime seconds
//    public void forward(View view) {
//        //check if we can go forward at forwardTime seconds before song endes
//        if ((timeElapsed + forwardTime) != 0) {
//            timeElapsed = timeElapsed + forwardTime;
//            //seek to the exact second of the track
//            mediaPlayer.seekTo((int) timeElapsed);
//        }
//    }
//
//    public void rewind(View view) {
//        //check if we can go forward at forwardTime seconds before song endes
//        if ((timeElapsed + backwardTime) != 0) {
//            timeElapsed = timeElapsed - backwardTime;
//            //seek to the exact second of the track
//            mediaPlayer.seekTo((int) timeElapsed);
//        }
//    }

}
