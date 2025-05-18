package com.waw.majorproject2.models;

import jakarta.persistence.*;

@Entity
public class CropResearchNote {
    @SequenceGenerator(name = "notes_generator", sequenceName = "notes_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notes_generator")
    @Id
    private Long noteId;
    private String note;
    @ManyToOne
    private WawUser researcher;
    @ManyToOne
    private Crop crop;
    private String date;


    public CropResearchNote() {
    }


    public CropResearchNote(Long noteId, String note, WawUser researcher, Crop crop, String date) {
        this.noteId = noteId;
        this.note = note;
        this.researcher = researcher;
        this.crop = crop;
        this.date = date;
    }



    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public WawUser getResearcher() {
        return researcher;
    }

    public void setResearcher(WawUser researcher) {
        this.researcher = researcher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
