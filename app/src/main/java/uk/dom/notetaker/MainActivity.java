package uk.dom.notetaker;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uk.dom.notetaker.adapters.NoteRecyclerAdapter;
import uk.dom.notetaker.model.Note;
import uk.dom.notetaker.presenters.NoteViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView noteRecycler;
    LinearLayoutManager linearLayoutManager;
    NoteRecyclerAdapter noteRecyclerAdapter;

    private NoteViewModel noteViewModel;

    public static final int NEW_NOTE_ACTIVITY_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteRecycler = findViewById(R.id.note_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        noteRecycler.setLayoutManager(linearLayoutManager);
        //noteRecyclerAdapter = new NoteRecyclerAdapter();
        //noteRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                noteRecyclerAdapter.setNotes(notes);
            }
        });

        //noteViewModel.insert(new Note("TEST","THIS IS A TEST NOTE", "HHHSDH"));

        noteRecycler.setAdapter(noteRecyclerAdapter);
    }

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_NOTE_ACTIVITY_CODE && resultCode == RESULT_OK){
            Note note = new Note(data.getStringExtra(NoteActivity.EXTRA_REPLY));
            noteViewModel.insert(note);
        } else{
            Toast.makeText(getApplicationContext(),
                    "Empty note not saved",
                    Toast.LENGTH_LONG).show();
        }
    }*/
}
