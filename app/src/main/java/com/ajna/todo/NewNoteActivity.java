package com.ajna.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class NewNoteActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etNotes;
    FloatingActionButton fabOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note_activity);

        etTitle = findViewById(R.id.et_title);
        etNotes = findViewById(R.id.et_notes);
        fabOk = findViewById(R.id.fab_ok);

        fabOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String details = etNotes.getText().toString();

                if(title.trim().length() == 0){
                    title = "<no title>";
                }
                if(details.trim().length() == 0){
                    details = "<no details>";
                }
                Intent intent = new Intent();
                intent.putExtra("TITLE", title);
                intent.putExtra("DETAILS", details);
//                Intent intent = new Intent();
//                intent.putExtra("TITLE", "tite");
//                intent.putExtra("DETAILS", "dite");

                setResult(2, intent);
                finish();
            }
        });

    }
}
