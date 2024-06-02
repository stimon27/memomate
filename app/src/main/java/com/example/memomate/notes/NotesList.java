package com.example.memomate.notes;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NotesList extends AppCompatActivity {

    private FirebaseFirestore db;
    private final List<Note> notes = new ArrayList<>();
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes_list);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.), (v, insets) -> {
//            WindowInsetsCompat insetsCompat = ViewCompat.onApplyWindowInsets(v, insets);
//            Insets insets1 = insetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(insets1.left, insets1.top, insets1.right, insets1.bottom);
//            return insetsCompat;
//        });
        db = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesAdapter(notes);
        recyclerView.setAdapter(adapter);
        getNotes();
    }

    private void getNotes() {
        db.collection("notes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Note note = document.toObject(Note.class);
                                note.setId(document.getId());
                                notes.add(note);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            adapter.updateNotesList(notes);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}