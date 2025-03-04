package com.waw.majorproject2.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class NokthulaAI {
    @Id
    @SequenceGenerator(name = "ai_generator", sequenceName = "ai_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ai_generator")
    private Long id;

    @OneToOne
    private WawUser owner;

    @OneToMany
    private List<TextMessage> thread;

    public NokthulaAI() {
        this.thread = new ArrayList<>();
    }

    public NokthulaAI(Long id, WawUser owner, List<TextMessage> thread) {
        this.id = id;
        this.owner = owner;
        this.thread = thread;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WawUser getOwner() {
        return owner;
    }

    public void setOwner(WawUser owner) {
        this.owner = owner;
    }

    public List<TextMessage> getThread() {
        return thread;
    }

    public void setThread(List<TextMessage> thread) {
        this.thread = thread;
    }
}
