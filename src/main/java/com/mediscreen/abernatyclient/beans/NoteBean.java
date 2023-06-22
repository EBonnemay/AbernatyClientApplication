package com.mediscreen.abernatyclient.beans;

public class NoteBean {
    private String noteId;
    private String patId;
    private String contentNote;

    public NoteBean(String patId, String contentNote) {
        this.patId = patId;
        this.contentNote = contentNote;

    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
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
