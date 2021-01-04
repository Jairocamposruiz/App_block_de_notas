package com.tokioschool.domain;

import com.tokioschool.util.DateUtil;

import java.time.LocalDate;

import static com.tokioschool.properties.PropertiesAPP.TYPE_NOTE;
import static com.tokioschool.properties.PropertiesAPP.TYPE_REMINDER;

public class Note {
    private String title;
    private String description;
    private LocalDate date;
    private String type;
    private int id;

    public Note(){}

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        date = LocalDate.now();
        type = TYPE_NOTE;
    }

    public Note(String title, String description, LocalDate date) {
        this.title = title;
        this.description = description;
        this.date = date;
        type = TYPE_REMINDER;
    }

    public Note(String title) {
        this.title = title;
        this.date = LocalDate.now();
        type = TYPE_NOTE;
        description = "";
    }

    public Note(String title, LocalDate date) {
        this.title = title;
        this.date = date;
        type = TYPE_REMINDER;
        description = "";
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Note)) return false;

        Note note = (Note) obj;
        if(!note.getTitle().equals(title)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "( " + type.toLowerCase() + " ) " + DateUtil.localDateFormat(date) + " -> " + title;
    }
}
