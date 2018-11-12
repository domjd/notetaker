package uk.dom.notetaker.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    public NoteRepository(Application application){
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return mAllNotes;
    }

    public LiveData<List<Note>> getSearchedNotes(String searchTerm){
        return mNoteDao.getSearchedNotes(searchTerm);
    }

    public LiveData<Note> getNoteDetails(int noteID){
        return mNoteDao.getNoteDetails(noteID);
    }

    public void updateNote(Note noteToUpdate){
        new updateAsyncTask(mNoteDao).execute(noteToUpdate);
    }

    public void insert(Note note){
        new insertAsyncTask(mNoteDao).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao mAsyncTaskDao;

        public insertAsyncTask(NoteDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(Note... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao mAsyncDao;

        public updateAsyncTask(NoteDao mAsyncDao) {
            this.mAsyncDao = mAsyncDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncDao.updateNote(notes[0].getNoteID(),notes[0].getTitle(), notes[0].getContent());
            return null;
        }
    }

}
