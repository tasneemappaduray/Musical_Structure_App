package com.example.android.musicalstructureapp;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FilmActivity extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;
    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /** This listener gets triggered when the {@link MediaPlayer} has completed playing the audio file. */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            /** Now that the sound file has finished playing, release the media player resources.*/
            releaseMediaPlayer();
        }
    };

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                /** The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a short amount of time.
                 * The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that our app is allowed to continue playing sound
                 * but at a lower volume. We'll treat both cases the same way because our app is playing short sound files.*/

                /** Pause playback and reset player to the start of the file. That way, we can play the word from the
                 * beginning when we resume playback.*/
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                /** The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.*/
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                /** The AUDIOFOCUS_LOSS case means we've lost audio focus and
                 * Stop playback and clean up resources*/
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);

        /** Create and setup the {@link AudioManager} to request audio focus */
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Music> music = new ArrayList<Music>();
        music.add(new Music("Flashdance", "What a feeling",R.raw.irene_cara_flashdance_what_a_feeling));
        music.add(new Music("Working Girl", "Let the river run",R.raw.carly_simon_let_the_river_run));
        music.add(new Music("Mad Max", "We don't need another hero",R.raw.tina_turner_we_dont_need_anotherh_hero));
        music.add(new Music("The Breakfast Club", "Don't you forget about me",R.raw.simple_minds_dont_you_forget_about_me_the_breakfast_club));
        music.add(new Music("The Lego Movie", "Everything is awesome",R.raw.the_lego_movie_everyrhing_is_awesome));

        MusicAdapter adapterM = new MusicAdapter(this, music);

        ListView musicListView = findViewById(R.id.music_list);

        musicListView.setAdapter(adapterM);

        musicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /** Release the media player if it currently exists because we are about to play a different sound file*/
                releaseMediaPlayer();
                /** Get the {@link Word} object at the given position the user clicked on */
                Music mMusic = music.get(position);
                /** Request audio focus so in order to play the audio file. The app needs to play a short audio file,
                 * so we will request audio focus with a short amount of time with AUDIOFOCUS_GAIN_TRANSIENT.*/
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    /** We have audio focus now.*/
                    /** Create and setup the {@link MediaPlayer} for the audio resource associated with the current word */
                    mMediaPlayer = MediaPlayer.create(FilmActivity.this, mMusic.getAudioResourceId());
                    /**Start the audio file */
                    mMediaPlayer.start();
                    /** Setup a listener on the media player, so that we can stop and release the
                     * media player once the sound has finished playing.*/
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        /**When the activity is paused, release the media player resources because we won't be playing any more sounds.*/
        releaseMediaPlayer();
    }

    /** Clean up the media player by releasing its resources. */
    private void releaseMediaPlayer() {
        /** If the media player is not null, then it may be currently playing a sound.*/
        if (mMediaPlayer != null) {
            /**Regardless of the current state of the media player, release its resources because we no longer need it.*/
            mMediaPlayer.release();

            /**Set the media player back to null. For our code, we've decided that
             * setting the media player to null is an easy way to tell that the media player
             * is not configured to play an audio file at the moment.
             */
            mMediaPlayer = null;
            /** Regardless of whether or not we were granted audio focus, abandon it. This also
             * unregisters the AudioFocusChangeListener so we don't get anymore callbacks.*/
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
