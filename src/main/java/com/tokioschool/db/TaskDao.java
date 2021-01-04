package com.tokioschool.db;

import com.tokioschool.domain.Note;
import com.tokioschool.domain.Task;
import com.tokioschool.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {
    private Connection connection;
    private final static String DATABASE_NAME = "src/main/resources/database/tasks.db";
    private final static String SQLITE_URI = "jdbc:sqlite:" + DATABASE_NAME;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(SQLITE_URI);
    }

    public void close() throws SQLException {
        connection.close();
    }

    public void addTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, repeat, finalize) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, task.getTitle());
        statement.setInt(2, task.getRepeat());
        statement.setInt(3, task.getFinalize());
        statement.executeUpdate();
    }

    public List<Task> getTask() throws SQLException {
        String sql = "SELECT * FROM tasks";
        PreparedStatement statement = connection.prepareStatement(sql);
        return getResultList(statement);
    }

    private List<Task> getResultList(PreparedStatement statement) throws SQLException {
        ResultSet result = statement.executeQuery();
        List<Task> tasks = new ArrayList<>();
        while (result.next()) {
            Task task = new Task();
            task.setTitle(result.getString(1));
            task.setRepeat(result.getInt(2));
            task.setFinalize(result.getInt(3));
            task.setId(result.getInt(4));
            tasks.add(task);
        }
        return tasks;
    }

    public boolean deleteTask(Task task) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, task.getId());
        int rows = statement.executeUpdate();

        return rows != 0;
    }

    public boolean editTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET title = ?, repeat = ?, finalize = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, task.getTitle());
        statement.setInt(2, task.getRepeat());
        statement.setInt(3, task.getFinalize());
        statement.setInt(4, task.getId());
        int rows = statement.executeUpdate();

        return rows != 0;
    }
}
