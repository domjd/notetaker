package uk.dom.notetaker.presenters;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import uk.dom.notetaker.model.Note;
import uk.dom.notetaker.model.NoteRepository;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;

    private LiveData<List <Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    public LiveData<List <Note>> getAllNotes(){
        return mAllNotes;
    }

    public LiveData<List <Note>> getSearchedNotes(String searchTerm){
        return mRepository.getSearchedNotes(searchTerm);
    }

    public void insert (Note note){
        mRepository.insert(note);
    }

    public LiveData<Note> getNote(int noteID){
        return mRepository.getNoteDetails(noteID);
    }

    public void updateNote(Note note){
        mRepository.updateNote(note);
    }
}
