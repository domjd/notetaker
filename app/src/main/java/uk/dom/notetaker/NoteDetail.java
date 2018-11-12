package uk.dom.notetaker;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import uk.dom.notetaker.model.Note;
import uk.dom.notetaker.presenters.NoteViewModel;

public class NoteDetail extends AppCompatActivity {

    NoteViewModel noteViewModel;

    FloatingActionButton addNoteFAB;
    TextView noteTitleTV;
    TextView noteContentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        addNoteFAB = findViewById(R.id.add_note_fab);
        noteTitleTV = findViewById(R.id.add_note_title);
        noteContentTV = findViewById(R.id.add_note_content);

        final int NOTE_ID = getIntent().getIntExtra("note_id", -1);


        addNoteFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(NOTE_ID != -1){
                    //save edited note
                    Note n = new Note(noteTitleTV.getText().toString(), noteContentTV.getText().toString());
                    n.setNoteID(NOTE_ID);
                    noteViewModel.updateNote(n);

                    Intent intent = new Intent(getApplicationContext(), NavNoteActivity.class);
                    startActivity(intent);
                }
                else {

                    Note note = new Note(noteTitleTV.getText().toString(), noteContentTV.getText().toString());
                    noteViewModel.insert(note);

                    Intent intent = new Intent(getApplicationContext(), NavNoteActivity.class);
                    startActivity(intent);
                }
            }
        });


        if(NOTE_ID != -1){
            noteViewModel.getNote(NOTE_ID).observe(this, new Observer<Note>() {
                @Override
                public void onChanged(@Nullable Note note) {
                    noteTitleTV.setText(note.getTitle());
                    noteContentTV.setText(note.getContent());
                }
            });
        }
        else{
            return;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
