package com.waw.majorproject2.models;

import jakarta.persistence.*;

@Entity
public class TextMessage {
    @Id
    @SequenceGenerator( name = "text_generator", initialValue = 1, sequenceName = "text_generator")
    @GeneratedValue(generator = "text_generator", strategy = GenerationType.SEQUENCE)
    private Long textId;
    private String userSaid;
    private String nokthulaSaid;

    public TextMessage(Long textId, String userSaid, String nokthulaSaid) {
        this.textId = textId;
        this.userSaid = userSaid;
        this.nokthulaSaid = nokthulaSaid;
    }

    public TextMessage() {
    }

    public Long getTextId() {
        return textId;
    }

    public void setTextId(Long textId) {
        this.textId = textId;
    }

    public String getUserSaid() {
        return userSaid;
    }

    public void setUserSaid(String userSaid) {
        this.userSaid = userSaid;
    }

    public String getNokthulaSaid() {
        return nokthulaSaid;
    }

    public void setNokthulaSaid(String nokthulaSaid) {
        this.nokthulaSaid = nokthulaSaid;
    }
}
