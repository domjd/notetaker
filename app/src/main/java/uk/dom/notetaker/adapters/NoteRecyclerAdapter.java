package uk.dom.notetaker.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uk.dom.notetaker.R;
import uk.dom.notetaker.model.Note;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.NoteRecyclerViewHolder> {

    List<Note> notes;
    private NoteItemListener mNoteItemListener;

    public class NoteRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView noteTitle;
        public List<TextView> noteTags = new ArrayList<>();
        public TextView noteDescription;

        private NoteItemListener itemListener;

        public NoteRecyclerViewHolder(View v, NoteItemListener listener) {
            super(v);

            noteTitle = v.findViewById(R.id.note_title);
            noteDescription = v.findViewById(R.id.note_description);
            itemListener = listener;

            v.setOnClickListener(this);
/*            noteTags.add((TextView)v.findViewById(R.id.note_tags));
            noteTags.add((TextView)v.findViewById(R.id.note_tags2));
            noteTags.add((TextView)v.findViewById(R.id.note_tags3));
            noteTags.add((TextView)v.findViewById(R.id.note_tags4));
            noteTags.add((TextView)v.findViewById(R.id.note_tags5));*/
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Note note = getItem(position);
            itemListener.onNoteClick(note);
        }
    }

    public NoteRecyclerAdapter(NoteItemListener noteItemListener) {
        super();
        mNoteItemListener = noteItemListener;
    }

    @Override
    public NoteRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card, parent, false);

        NoteRecyclerViewHolder holder = new NoteRecyclerViewHolder(v, mNoteItemListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(NoteRecyclerViewHolder holder, int position) {
        holder.noteTitle.setText(notes.get(position).getTitle());
        holder.noteDescription.setText(notes.get(position).getContent());

/*        try{
            JSONObject jsonObject = new JSONObject(notes.get(position).getTagArrayString());

            JSONArray noteTags = jsonObject.optJSONArray("tagArray");
            if(noteTags != null){
                for(int i = 0; i < noteTags.length(); i++){
                        //noteTags.get(i);
                        holder.noteTags.get(i).setText(noteTags.get(i).toString());
                        holder.noteTags.get(i).setVisibility(View.VISIBLE);
                    }
                }


        }catch (JSONException e){
            throw new RuntimeException(e);
        }*/

    }

    public Note getItem(int position){
        return notes.get(position);
    }

    public void setNotes(List<Note> n){
        notes = n;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(notes != null)
            return notes.size();
        else return 0;
    }
}

