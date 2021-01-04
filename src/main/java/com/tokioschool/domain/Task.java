package com.tokioschool.domain;

public class Task {
    private String title;
    private boolean finalize;
    private boolean repeat;
    private int id;

    public Task(){}

    public Task(String title, boolean repeat) {
        this.title = title;
        this.repeat = repeat;
        finalize = false;
    }

    public void finalizeTask() {
        finalize = true;
    }

    public void resetTask() {
        finalize = false;
    }

    public String getTitle() {
        return title;
    }

    public int getFinalize() {
        if(finalize) return 1;
        return 0;
    }

    public int getRepeat() {
        if(repeat) return 1;
        return 0;
    }

    public boolean isFinalize() {
        return finalize;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFinalize(int finalize) {
        if(finalize == 0) {
            this.finalize = false;
        } else {
            this.finalize = true;
        }

    }

    public void setRepeat(int repeat) {
        if(repeat == 0) {
            this.repeat = false;
        } else {
            this.repeat = true;
        }
    }

    public int getId() {
        return id;
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
        if(!(obj instanceof Task)) return false;

        Task task = (Task) obj;
        if(!task.getTitle().equals(title)) return false;

        return true;
    }

    @Override
    public String toString() {
        if(isRepeat()){
            if(isFinalize()){
                return "( Daily Task ) - ( ✓ ) -> " + title;
            }
            return "( Daily Task ) - ( X ) -> " + title;
        }
        return "( Task ) - ( X ) -> " + title;
    }
}
