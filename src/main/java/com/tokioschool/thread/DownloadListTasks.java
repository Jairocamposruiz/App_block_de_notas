package com.tokioschool.thread;

import com.tokioschool.db.TaskDao;
import com.tokioschool.domain.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;


public class DownloadListTasks implements Callable<List<Task>> {
    private List<Task> tasks;

    private List<Task> downloadListTasks() throws SQLException {
        TaskDao dbTask = new TaskDao();
        dbTask.connect();
        tasks = dbTask.getTask();
        dbTask.close();
        return tasks;
    }

    @Override
    public List<Task> call() throws Exception {
        return downloadListTasks();
    }
}
