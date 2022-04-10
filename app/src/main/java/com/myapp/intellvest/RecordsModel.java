package com.myapp.intellvest;

import com.google.firebase.database.Exclude;

public class RecordsModel {
    private String time;
    private String message;
    private String mKey;

    public RecordsModel() {
    }

    public RecordsModel(String time, String message) {
        this.time = time;
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}
