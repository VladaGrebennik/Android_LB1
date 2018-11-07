package com.example.hp.notesapplication;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NotesList implements Serializable {

    private List<Notes> notes;

    public  NotesList(){
        notes = new ArrayList<Notes>();
    }

    public  void Add(Notes note){
        notes.add(note);
    }
    public void setList(List<Notes> list) {
        notes = list;
    }

    public List<Notes> getList() {
        return notes;
    }
}

