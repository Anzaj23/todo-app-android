package com.ajna.todo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_NOTE_REQUEST = 2;
    NotesData notes = new NotesData();

    private FloatingActionButton fabAdd;
    private RecyclerView rvNotes;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    private Toolbar mainToolbar;
    static boolean isDataReadIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!isDataReadIn) {
            notes.readNotes(getApplicationContext());
            isDataReadIn = true;
        }

        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        fabAdd = findViewById(R.id.fab_add);

        rvNotes = findViewById(R.id.rv_notes);
        rvLayoutManager = new LinearLayoutManager(this);
        rvNotes.setLayoutManager(rvLayoutManager);
        rvNotes.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        rvAdapter = new RVAdapter(notes.getNotes());
        rvNotes.setAdapter(rvAdapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_REQUEST);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete_all) {
            int size = notes.getNotes().size();
            notes.deleteAll();
            rvAdapter.notifyItemRangeRemoved(0, size);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_NOTE_REQUEST){
            String title = data.getStringExtra("TITLE");
            String details = data.getStringExtra("DETAILS");

            Note note = new Note(title, details);
            notes.addNote(note);
        }
    }

    @Override
    protected void onStop() {
        notes.saveNotes(getApplicationContext());
        super.onStop();
    }
}
