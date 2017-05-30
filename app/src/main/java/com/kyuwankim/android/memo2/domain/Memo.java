package com.kyuwankim.android.memo2.domain;

/**
 * Created by kimkyuwan on 2017. 5. 30..
 */

public class Memo {

    private String id;
    private String title;
    private String date;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
