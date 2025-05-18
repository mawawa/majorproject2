package com.waw.majorproject2.models;


import jakarta.persistence.*;

@Entity
public class FarmOfficerNote {
    @Id
    @SequenceGenerator(name = "officer_note_generator", sequenceName = "officer_note_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "officer_note_generator")
    private Long noteId;
    private String note;

    @ManyToOne
    private WawUser officer;

    private String date;

    @ManyToOne
    private Farm farm;

    public FarmOfficerNote(Long noteId, String note, WawUser researcher, String date, Farm farm) {
        this.noteId = noteId;
        this.note = note;
        this.officer = researcher;
        this.date = date;
        this.farm = farm;
    }



    public FarmOfficerNote() {
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
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

    public WawUser getOfficer() {
        return officer;
    }

    public void setOfficer(WawUser officer) {
        this.officer = officer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
