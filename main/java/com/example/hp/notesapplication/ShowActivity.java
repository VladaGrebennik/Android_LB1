package com.example.hp.notesapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    TextView titleShow,textShow,impShow;
    ImageView photoShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show2);

        titleShow = (TextView) findViewById(R.id.titleShow);
        textShow = (TextView) findViewById(R.id.descShow);
        impShow = (TextView) findViewById(R.id.impShow);
        photoShow = (ImageView) findViewById(R.id.photoShow);

        Bundle arguments = getIntent().getExtras();
        final Notes note;
        note = (Notes) arguments.getSerializable("Tshow");

        titleShow.setText(note.getName());
        textShow.setText(note.getDescription());
        photoShow.setImageURI(Uri.parse(note.getImage()));
        if(note.getNoteClass() ==1){
             impShow.setText("not important");
        }
        else if(note.getNoteClass()==2){
             impShow.setText("important");
        }
        else{
            impShow.setText("very important");
        }


    }
}
