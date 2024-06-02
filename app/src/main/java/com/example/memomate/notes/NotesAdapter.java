package com.example.memomate.notes;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> notesList;

    public NotesAdapter(List<Note> notesList) {
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Note note = notesList.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.contentTextView.setText(note.getContent());
        holder.dateTextView.setText(dateFormat.format(note.getCreatedDate()));
        holder.deleteButton.setOnClickListener(v -> removeItem(position, holder.itemView.getContext()));
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView contentTextView;
        public TextView dateTextView;
        public Button deleteButton;

        public NoteViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.noteTitle);
            contentTextView = view.findViewById(R.id.noteContent);
            dateTextView = view.findViewById(R.id.noteDate);
            deleteButton = view.findViewById(R.id.deleteButton);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateNotesList(List<Note> notesList) {
        this.notesList = notesList;
        notifyDataSetChanged();
    }

    public void removeItem(int position, Context context) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("notes").document(notesList.get(position).getId())
                .delete()
                .addOnSuccessListener(aVoid -> {
                        notesList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, notesList.size());
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error deleting document", e);
                    Toast.makeText(context, "Error deleting note", Toast.LENGTH_SHORT).show();
                });
    }
}