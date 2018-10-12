package com.example.android.musicalstructureapp;


public class Music {

    /** The artist name  */
    private String mMusicArtist;
    /** The title of the song */
    private String mMusicTitle;
    private int mAudioResourceId;

    /**
     * Create a new Music object.
     * @param musicArtist is the person who sings the song
     * @param musicTitle is the title of the song
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    public Music(String musicArtist, String musicTitle, int audioResourceId){
        mMusicArtist = musicArtist;
        mMusicTitle = musicTitle;
        mAudioResourceId = audioResourceId;
    }


    /**Get the artist who sings the song */
    public String getMusicArtist(){
        return mMusicArtist;
    }

    /**Get the title of the song */
    public String getMusicTitle(){
        return mMusicTitle;
    }

    /** Get the Audio from the resource ID**/
    public int getAudioResourceId(){
        return mAudioResourceId;
    }

}
