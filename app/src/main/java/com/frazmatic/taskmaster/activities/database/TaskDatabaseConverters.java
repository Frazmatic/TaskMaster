package com.frazmatic.taskmaster.activities.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class TaskDatabaseConverters {
    //Copied from Demo: https://github.com/codefellows/seattle-code-java-401d15/blob/main/class-29/Zorkdemo/app/src/main/java/com/zork/zork_demo/database/GameDatabaseConverters.java
    @TypeConverter
    public static Date fromTimeStamp(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }
}
