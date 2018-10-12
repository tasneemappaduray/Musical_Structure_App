package com.example.android.musicalstructureapp;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicAdapter extends ArrayAdapter<Music> {

//    private int mColorResourceId;

    /**
     * @param context  The current context. Used to inflate the layout file.
     * @param music A List of Music objects to display in a list
     */
    public MusicAdapter(Activity context, ArrayList<Music> music) {

        super(context, 0, music);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /**Check if the existing view is being reused, otherwise inflate the view **/
        View listMusicView = convertView;
        if(listMusicView == null) {
            listMusicView = LayoutInflater.from(getContext()).inflate(
                    R.layout.music_item, parent, false);
        }

        /**Get the {@link Music} object located at this position in the list **/
        Music currentMusic = getItem(position);


        /** Find the TextView in the music_item.xml layout with the ID artist_text_view **/
        TextView artistTextView = listMusicView.findViewById(R.id.artist_text_view);
        /**Get the Artist name from the current music object and set this text on the Artist TextView **/
        artistTextView.setText(currentMusic.getMusicArtist());

        /**Find the TextView in the music_item.xml layout with the ID title_text_view **/
        TextView titleTextView = listMusicView.findViewById(R.id.title_text_view);
        /**Get the Title of the song from the current Music object and set this text on the Title TextView **/
        titleTextView.setText(currentMusic.getMusicTitle());

//        ImageButton imageButton = (ImageButton) listMusicView.findViewById(R.id.play_image);
//        imageButton.setImageResource(currentMusic.getResourceID());

//        /** Set the theme color for the list item */
//        View textContainer = listMusicView.findViewById(R.id.text_container);
//        /** Find the color that the resource ID maps to */
//        int color = ContextCompat.getColor(getContext(), mColorResourceId);
//        /** Set the background color of the text container View */
//        textContainer.setBackgroundColor(color);


        /**Return the whole list item layout (containing 2 TextViews)so that it can be shown in the ListView **/
        return listMusicView;
    }

}