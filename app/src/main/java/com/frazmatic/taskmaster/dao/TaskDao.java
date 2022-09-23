package com.frazmatic.taskmaster.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.frazmatic.taskmaster.models.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    public void insertTask(Task task);

    @Query("SELECT * FROM Task")
    public List<Task> findAll();

    @Query("SELECT * FROM Task WHERE state = :state")
    public List<Task> findAllByState(Task.TaskState state);

    @Query("SELECT * FROM Task ORDER BY state ASC")
    public List<Task> findAllSortedByName();

    @Query("SELECT * FROM Task WHERE id = :id")
    public Task findByAnId(long id);
}
