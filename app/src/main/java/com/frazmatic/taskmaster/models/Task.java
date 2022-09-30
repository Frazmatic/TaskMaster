package com.frazmatic.taskmaster.models;

import androidx.annotation.NonNull;

import java.util.Date;

public class Task {

    private String title;
    private String body;
    private TaskState state;
    private Date created;

    public Task(String title, String body) {
        this.title = title;
        this.body = body;
        this.state = TaskState.NEW;
        this.created =  new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public enum TaskState {
        NEW("New"), ASSIGNED("Assigned"), IN_PROGRESS("In Progress"), COMPLETE("Complete");
        private final String state;

        TaskState(String _state) {
            this.state = _state;
        }

        @NonNull
        @Override
        public String toString() {
            return state != null ? state : "";
        }

        public TaskState fromString(String _state){
            for(TaskState s : TaskState.values()){
                if(s.state.equals(_state)){
                    return s;
                }
            }
            return null;
        }
    }

}
