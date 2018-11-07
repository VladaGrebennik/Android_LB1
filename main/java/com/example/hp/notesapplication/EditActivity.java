package com.example.hp.notesapplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    int noteclass=0;
    EditText titleEditText,descTextEdit;
    RadioButton one,two,three;
    Bitmap bitmap;
    String uriStr;
    ImageView photoViewEdit;

    static final int GALLERY_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        titleEditText = (EditText) findViewById(R.id.titleEditText);
        descTextEdit = (EditText) findViewById(R.id.descTextEdit);
        photoViewEdit = (ImageView) findViewById(R.id.photoViewEdit);

        Bundle arguments = getIntent().getExtras();
        final Notes note;
        note = (Notes) arguments.getSerializable("noteinfo");

        titleEditText.setText(note.getName());
        descTextEdit.setText(note.getDescription());
        photoViewEdit.setImageURI(Uri.parse(note.getImage()));
        uriStr=note.getImage();

        one = (RadioButton) findViewById(R.id.not_importantEdit);
        two = (RadioButton) findViewById(R.id.importantEdit);
        three = (RadioButton) findViewById(R.id.very_importantEdit);

         if(note.getNoteClass()==1){
             one.setChecked(true);
            //one.isChecked();
         }
        else if(note.getNoteClass()==2){
            two.setChecked(true);
        }
        else if(note.getNoteClass()==3){
             three.setChecked(true);
         }

        RadioGroup radGrp = (RadioGroup)findViewById(R.id.radiosEdit);
        radGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch(id) {
                    case R.id.not_importantEdit:
                        noteclass=1;
                        break;
                    case R.id.importantEdit:
                        noteclass=2;
                        break;
                    case R.id.very_importantEdit:
                        noteclass=3;
                    default:
                        break;
                }
            }});

        Button button = (Button)findViewById(R.id.editPhoto);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        ImageView imageView = (ImageView) findViewById(R.id.photoViewEdit);
        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = imageReturnedIntent.getData();
                    uriStr = selectedImage.toString();

                    try {
                        final InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                        bitmap = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    public void editNote(View view) {
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        descTextEdit = (EditText) findViewById(R.id.descTextEdit);
        Date date = new Date();
        Notes noteTest = new Notes(titleEditText.getText().toString(), descTextEdit.getText().toString(), noteclass, date,uriStr);
        Intent intent = new Intent();
        intent.putExtra("secret",noteTest);
        setResult(RESULT_OK, intent);
        finish();
    }
}

