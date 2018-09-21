package com.ajna.todo;

import java.io.Serializable;

public class Note implements Serializable{

    private long serialVersionUID = 1L;

    private String noteTitle;
    private String noteDetails;

    public Note(String noteTitle, String noteDetails) {
        this.noteTitle = noteTitle;
        this.noteDetails = noteDetails;
    }


    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDetails() {
        return noteDetails;
    }

    @Override
    public String toString() {
        return("Note title: "+ noteTitle + ". Note details: "+noteDetails);
    }
}
