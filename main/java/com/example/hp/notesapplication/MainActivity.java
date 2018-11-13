package com.example.hp.notesapplication;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Debug;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    NotesList listOfNotes = new NotesList();
    private static  final int REQUEST_ACCESS_TYPE=1;
    private static  final int REQUEST_ACCESS_TYPE_2=2;
    private int position;
    final int MENU_ITEM_EDIT=1;
    final int MENU_ITEM_DELETE=2;
    StateAdapter stateAdapter;
    ListView notesList;
    TextView titleShow,textShow,impShow,resultText;
    String addString,deleteString,editString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = (TextView) findViewById((R.id.result1));
        notesList = (ListView) findViewById(R.id.notesList);
        stateAdapter = new StateAdapter(this, R.layout.list_item, listOfNotes.getList());
        registerForContextMenu(notesList);
        addString  = getResources().getString(R.string.add);
        editString  = getResources().getString(R.string.edit);
        deleteString  = getResources().getString(R.string.delete);

        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(),ShowActivity.class);
//                Notes notetest;
//                notetest =(Notes)stateAdapter.getItem(position);
//                intent.putExtra("Tshow",notetest);
                intent.putExtra("Tshow",(Notes)stateAdapter.getItem(position));
               startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Type your keyword here");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setActivated(true);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    NotesList newList = new NotesList();
//                    for(int i=0;i<listOfNotes.getList().size();i++){
//                            if(listOfNotes.getList().get(i).getName().equals(query)){
//                            newList.Add(listOfNotes.getList().get(i));
//                        }
//                    }
                    for(int i=0;i<listOfNotes.getList().size();i++){
                        if(listOfNotes.getList().get(i).getDescription().contains(query)){
                            newList.Add(listOfNotes.getList().get(i));
                        }
                    }
                    stateAdapter = new StateAdapter(getApplicationContext(), R.layout.list_item, newList.getList());
                    notesList.setAdapter(stateAdapter);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    stateAdapter.getFilter().filter(newText);
                    return false;
                }
            });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        List<Notes> list= new ArrayList<Notes>();

        int id = item.getItemId();
        switch(id){
            case R.id.add_settings :
                Intent intent = new Intent(this, SecondActivity.class);
                startActivityForResult(intent, REQUEST_ACCESS_TYPE);
                return true;

            case R.id.filter_settings:

                return true;

            case R.id.main_settings:
                stateAdapter = new StateAdapter(getApplicationContext(), R.layout.list_item, listOfNotes.getList());
                notesList.setAdapter(stateAdapter);
                return true;

            case R.id.menu_imp_n:
                for(int i=0;i<listOfNotes.getList().size();i++){
                    if(listOfNotes.getList().get(i).getNoteClass()==1){
                        list.add(listOfNotes.getList().get(i));
                    }
                }
                stateAdapter = new StateAdapter(getApplicationContext(), R.layout.list_item, list);
                notesList.setAdapter(stateAdapter);
                return true;
            case R.id.menu_imp_i:
                for(int i=0;i<listOfNotes.getList().size();i++){
                    if(listOfNotes.getList().get(i).getNoteClass()==2){
                        list.add(listOfNotes.getList().get(i));
                    }
                }
                stateAdapter = new StateAdapter(getApplicationContext(), R.layout.list_item, list);
                notesList.setAdapter(stateAdapter);
                return true;

            case R.id.menu_imp_v:
                for(int i=0;i<listOfNotes.getList().size();i++){
                    if(listOfNotes.getList().get(i).getNoteClass()==3){
                        list.add(listOfNotes.getList().get(i));
                    }
                }
                stateAdapter = new StateAdapter(getApplicationContext(), R.layout.list_item, list);
                notesList.setAdapter(stateAdapter);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       TextView resultText = (TextView) findViewById((R.id.result1));
       String name, desc, image;
       int notecl;

        Notes note;
        Date date= new Date();
        if (requestCode == REQUEST_ACCESS_TYPE) {
            if (resultCode == RESULT_OK) {//
                name=data.getStringExtra("key1");
                desc=data.getStringExtra("key2");
                image=data.getStringExtra("key4");
                notecl=data.getIntExtra("key3",0);

                note= new Notes(name,desc,notecl,date,image);
                listOfNotes.Add(note);
                resultText.setText( listOfNotes.getList().size()+" notes now");
                notesList.setAdapter(stateAdapter);
            }
        }

        if (requestCode == REQUEST_ACCESS_TYPE_2){
            if (resultCode == RESULT_OK) {
                note = (Notes) data.getSerializableExtra("secret");
                listOfNotes.getList().get(position).setDescription(note.getDescription());
                listOfNotes.getList().get(position).setName(note.getName());
                listOfNotes.getList().get(position).setCrTime(note.getCrTime());
                listOfNotes.getList().get(position).setImage(note.getImage());
                listOfNotes.getList().get(position).setNoteClass(note.getNoteClass());
                stateAdapter.notifyDataSetChanged();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.notesList) {
            ListView notesList = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Notes obj = (Notes) notesList.getItemAtPosition(acmi.position);
            menu.add(0,MENU_ITEM_EDIT,0,editString);
            menu.add(0,MENU_ITEM_DELETE,0,deleteString);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        TextView resultText = (TextView) findViewById((R.id.result1));
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {

            case MENU_ITEM_EDIT:
                Intent intent = new Intent(this, EditActivity.class);
//                Notes note;
//                note=(Notes)stateAdapter.getItem(info.position);
                intent.putExtra("noteinfo",(Notes)stateAdapter.getItem(info.position));
                position = info.position;
                startActivityForResult(intent, REQUEST_ACCESS_TYPE_2);
                break;
            case MENU_ITEM_DELETE:
                stateAdapter.remove(stateAdapter.getItem(info.position));
                resultText.setText("ok");
                stateAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        Notes note = new Notes();
//        note.setList(notes);
//        outState.putSerializable("key",note);
        NotesList currentList = new NotesList();
        currentList.setList(stateAdapter.getList());
        outState.putSerializable("currentList",currentList);
        outState.putSerializable("key",listOfNotes);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        Notes note = new Notes();
//        Notes note1 = new Notes();
//        note = (Notes) savedInstanceState.getSerializable("key");
//        notes = note.getList();
        //stateAdapter = new StateAdapter(this, R.layout.list_item, notes);

        listOfNotes = (NotesList) savedInstanceState.getSerializable("key");
        NotesList currentList = (NotesList) savedInstanceState.getSerializable("currentList");
        stateAdapter = new StateAdapter(this, R.layout.list_item, currentList.getList());
        notesList.setAdapter(stateAdapter);
    }
}
