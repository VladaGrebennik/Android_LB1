package com.example.hp.notesapplication;
import java.io.ByteArrayOutputStream;
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
import android.widget.RadioGroup;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    ArrayList<Notes> notes= new ArrayList<Notes>();
    public final static String secretString = "hello";
    int noteclass=1;
    EditText titleText,descText;
    static final int GALLERY_REQUEST = 1;
    Bitmap bitmap;
    String uriStr="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        titleText = (EditText) findViewById(R.id.titleText);
        descText = (EditText) findViewById(R.id.descText);
        RadioGroup radGrp = (RadioGroup)findViewById(R.id.radios);
        radGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch(id) {
                    case R.id.not_important:
                        noteclass=1;
                        break;
                    case R.id.important:
                        noteclass=2;
                        break;
                    case R.id.very_important:
                        noteclass=3;
                    default:
                        break;
                }
            }});

        Button button = (Button)findViewById(R.id.addPhoto);
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
        ImageView imageView = (ImageView) findViewById(R.id.photoView);
        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    uriStr=selectedImage.toString();

                    try {
                        final InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                        bitmap= BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    public void saveNote(View view){
        Intent intent = new Intent();
        intent.putExtra("key1",titleText.getText().toString());
        intent.putExtra("key2",descText.getText().toString());
        intent.putExtra("key3",noteclass);
        intent.putExtra("key4",uriStr);
        setResult(RESULT_OK, intent);
        finish();
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", titleText.getText().toString());
        outState.putString("text", descText.getText().toString());
        outState.putInt("class",noteclass);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        titleText.setText(savedInstanceState.getString("count"));
        titleText.setText(savedInstanceState.getString("title"));
    }
}
