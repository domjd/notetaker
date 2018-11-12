package uk.dom.notetaker.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@Entity(tableName = "notes_table")
public class Note {

    @NonNull
    @ColumnInfo(name = "note_title")
    private String title;

    @ColumnInfo(name = "note_content")
    private String content;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int noteID;


    public Note(String title, String content) {
        this.title = title;
        this.content = content;

/*        JSONObject tagsJson = new JSONObject();
        try {
            tagsJson.put("noteTags", new JSONArray(tags));
        }catch (JSONException e){
            throw new RuntimeException(e);
        }*/

        //List<String> tags = ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }
}
