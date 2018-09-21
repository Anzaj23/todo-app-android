package com.ajna.todo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class NotesData {
    private static final String fileName = "data.dat";
    private static List<Note> notes = new ArrayList<>();
    public List<Note> getNotes() {
        return notes;
    }


    public void addNote(Note note) {
        notes.add(note);
    }

    public void deleteAll() {
        notes.clear();
    }


    public void deleteNote(int position) {
        notes.remove(position);
    }

    public void readNotes(Context context) {
        File file = new File(context.getFilesDir(), fileName);
        if (file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
                Note note;
                    try {
                        System.out.println("CheckPoint 1");
                        note = (Note) inputStream.readObject();
                        while(note != null){
                            notes.add(note);
                            note = (Note) inputStream.readObject();
                        }
                    } catch (ClassNotFoundException e) {
                        System.out.println("ClassNotFound exception");
                    }

            } catch (FileNotFoundException e) {
                System.out.println("FileNotFound exception");
            } catch (IOException e) {
                System.out.println("IO Exception!");
                e.printStackTrace();
            }

        }
    }

    public void saveNotes(Context context) {
        File file = new File(context.getFilesDir(), fileName);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (Note note : notes) {
                outputStream.writeObject(note);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}