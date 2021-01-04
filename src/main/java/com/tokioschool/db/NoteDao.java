package com.tokioschool.db;

import com.tokioschool.domain.Note;
import com.tokioschool.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {
    private Connection connection;
    private final static String DATABASE_NAME = "src/main/resources/database/notes.db";
    private final static String SQLITE_URI = "jdbc:sqlite:" + DATABASE_NAME;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(SQLITE_URI);
    }

    public void close() throws SQLException {
        connection.close();
    }

    public void addNote(Note note) throws SQLException {
        String sql = "INSERT INTO notes (title, description, date, type) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, note.getTitle());
        statement.setString(2, note.getDescription());
        statement.setString(3, DateUtil.localDateFormat(note.getDate()));
        statement.setString(4, note.getType());
        statement.executeUpdate();
        //TODO el statement hay que cerrarlo de esta manera    statement.close();    lo descubrí despues.
        //TODO tambien se puede crear el PreparedStatement dentro de los parentesis de un try para que se autocierre.
    }

    public List<Note> getNotes() throws SQLException {
        String sql = "SELECT * FROM notes";
        PreparedStatement statement = connection.prepareStatement(sql);
        return getResultList(statement);
    }

    private List<Note> getResultList(PreparedStatement statement) throws SQLException {
        ResultSet result = statement.executeQuery();
        List<Note> notes = new ArrayList<>();
        while (result.next()) {
            Note note = new Note();
            note.setTitle(result.getString(1));
            note.setDescription(result.getString(2));
            note.setDate(DateUtil.localDateParse(result.getString(3)));
            note.setType(result.getString(4));
            note.setId(result.getInt(5));
            notes.add(note);
        }
        return notes;
    }

    public boolean deleteNote(Note note) throws SQLException {
        String sql = "DELETE FROM notes WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, note.getId());
        int rows = statement.executeUpdate();

        return rows != 0;
    }

    public boolean editNote(Note note) throws SQLException {
        String sql = "UPDATE notes SET title = ?, description = ?, date = ?, type = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, note.getTitle());
        statement.setString(2, note.getDescription());
        statement.setString(3, DateUtil.localDateFormat(note.getDate()));
        statement.setString(4, note.getType());
        statement.setInt(5, note.getId());
        int rows = statement.executeUpdate();

        return rows != 0;
    }
}
