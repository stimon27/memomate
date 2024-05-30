package com.example.memomate.notes;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.memomate.R;

public class NotesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes_list);
        Log.d("aaa", "blabla");
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.), (v, insets) -> {
//            WindowInsetsCompat insetsCompat = ViewCompat.onApplyWindowInsets(v, insets);
//            Insets insets1 = insetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(insets1.left, insets1.top, insets1.right, insets1.bottom);
//            return insetsCompat;
//        });
    }
}