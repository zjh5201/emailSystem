package com.email.po;

import java.util.Date;

public class Save {
    private Integer id;

    private String addresseeId;

    private String addresserId;

    private String title;

    private String filepath;

    private Date time;

    private Integer readed;

    private String content;

    private String filename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddresseeId() {
        return addresseeId;
    }

    public void setAddresseeId(String addresseeId) {
        this.addresseeId = addresseeId == null ? null : addresseeId.trim();
    }

    public String getAddresserId() {
        return addresserId;
    }

    public void setAddresserId(String addresserId) {
        this.addresserId = addresserId == null ? null : addresserId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getReaded() {
        return readed;
    }

    public void setReaded(Integer readed) {
        this.readed = readed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }
}