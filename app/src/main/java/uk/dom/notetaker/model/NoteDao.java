package uk.dom.notetaker.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Query("DELETE FROM notes_table")
    void deleteAll();

    @Query("SELECT * from notes_table ORDER BY note_title ASC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes_table WHERE note_title LIKE :searchTerm ORDER BY note_title ASC")
    LiveData<List<Note>> getSearchedNotes(String searchTerm);

    @Query("SELECT * FROM notes_table WHERE id IS :noteID")
    LiveData<Note> getNoteDetails(int noteID);

    @Query("UPDATE notes_table SET note_title = :noteTitle, note_content = :noteContent WHERE id = :noteID")
    void updateNote(int noteID, String noteTitle, String noteContent);
}
