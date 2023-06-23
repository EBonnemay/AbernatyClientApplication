package com.mediscreen.abernatyclient.beans;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class NoteBean {
    private String id;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String patId;
    private String contentNote;


    public NoteBean(String patId, String contentNote) {
        this.patId = patId;
        this.contentNote = contentNote;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

    }

    public String getId() {
        return id;
    }

    public void setNoteId(String noteId) {
        this.id = id;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getContentNote() {
        return contentNote;
    }

    public void setContentNote(String contentNote) {
        this.contentNote = contentNote;
    }
}
