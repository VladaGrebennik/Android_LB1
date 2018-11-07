package com.example.hp.notesapplication;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URI;
import java.util.*;
import java.text.*;
import java.io.Serializable;

public class Notes implements Serializable{
    private String name;
    private String description;
    private int noteClass;
    private Date crTime;
    private String image;
    //private List<Notes> notes;

    public Notes(String name,String description,int noteClass,Date crTime,String  image){
        this.name= name;
        this.description= description;
        this.noteClass=noteClass;
        this.crTime=crTime;
        this.image= image;
    }
    public Notes(String name,String description,int noteClass,Date crTime){
        this.name= name;
        this.description= description;
        this.noteClass=noteClass;
        this.crTime=crTime;
    }
//    Notes(){}
//    public List<Notes> getList() {
//        return notes;
//    }
//
//    public void setList(List<Notes> list) {
//        this.notes = list;
//    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getNoteClass() {
        return this.noteClass;
    }

    public Date getCrTime() {
        return this.crTime;
    }

    public String getImage() {
        return this.image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNoteClass(int noteClass) {
        this.noteClass = noteClass;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCrTime(Date crTime) {
        this.crTime = crTime;
    }
    public String dateTostring(Date crTime){
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        return  formatForDateNow.format(crTime);
    }
}
