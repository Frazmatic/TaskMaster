package com.frazmatic.taskmaster.activities.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.frazmatic.taskmaster.dao.TaskDao;
import com.frazmatic.taskmaster.models.Task;

@TypeConverters({TaskDatabaseConverters.class})
@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
