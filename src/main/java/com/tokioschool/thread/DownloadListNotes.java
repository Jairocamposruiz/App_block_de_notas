package com.tokioschool.thread;

import com.tokioschool.db.NoteDao;
import com.tokioschool.domain.Note;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class DownloadListNotes implements Callable<List<Note>> {
    private List<Note> notes;

    private List<Note> downloadListNotes() throws SQLException {
        NoteDao dbNote = new NoteDao();
        dbNote.connect();
        notes = dbNote.getNotes();
        dbNote.close();
        return notes;
    }

    @Override
    public List<Note> call() throws Exception {
        return downloadListNotes();
    }
}
