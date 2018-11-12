package uk.dom.notetaker.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {Note.class}, version = 3)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static volatile NoteRoomDatabase INSTANCE;

    static NoteRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (NoteRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class, "note_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
             new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final NoteDao mDao;

        PopulateDbAsync(NoteRoomDatabase db) {
            mDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();

            /*List<String> tags = new ArrayList<>();
            tags.add("#TagOne");
            tags.add("#TagTwo");
            tags.add("#TagThree");
            tags.add("#TagFour");
            tags.add("#TagFive");

            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put("tagArray", new JSONArray(tags));
            }catch (JSONException e){
                throw new RuntimeException(e);
            }

            String tagString = jsonObject.toString();

            Note note = new Note("test note", "This is a test to test the note description field of a recycler view", tagString);

            mDao.insert(note);*/

            return null;
        }
    }
}
