package com.tokioschool.comparator;

import com.tokioschool.domain.Note;

import java.util.Comparator;

public class ComparatorNoteForDate implements Comparator<Note> {
    @Override
    public int compare(Note note1, Note note2) {
        if(note1.getDate().isAfter(note2.getDate())) return 1;
        return -1;
    }
}
