package com.example.hp.notesapplication;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StateAdapter extends ArrayAdapter<Notes>  {

    private LayoutInflater inflater;
    private int layout;
    private List<Notes> notes;

    public StateAdapter(Context context, int resource, List<Notes> notes) {
        super(context, resource, notes);
        this.notes = notes;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }


    public List<Notes> getList(){
        return notes;
    }



    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");

        ImageView photoView = (ImageView) view.findViewById(R.id.photo);
        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView timeView = (TextView) view.findViewById(R.id.time);

        Notes note = notes.get(position);
        Uri s = Uri.parse(note.getImage());
        photoView.setImageURI(s);
        titleView.setText(note.getName());
        timeView.setText(formatForDateNow.format(note.getCrTime()));
        return view;
    }
}