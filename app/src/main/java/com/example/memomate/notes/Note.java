package com.example.memomate.notes;

import android.os.Build;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.PropertyName;

import java.time.Instant;
import java.util.Date;

public class Note {
    private String title;
    private String content;
    private Date createdDate;
    private String createdBy;
    private String id;

    public Note() {
    }

    public Note(String title, String content, String createdBy) {
        this.title = title;
        this.content = content;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.createdDate = Date.from(Instant.now());
        }
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @PropertyName("createdDate")
    public Date getCreatedDate() {
        return createdDate;
    }

    @PropertyName("createdDate")
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate.toDate();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
