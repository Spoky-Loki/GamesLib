package com.example.WebApp.models;

import jakarta.persistence.*;

@Entity
public class Lib {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private Long game_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getGame_id() {
        return game_id;
    }

    public void setGame_id(Long game_id) {
        this.game_id = game_id;
    }

    public Lib() {
    }

    public Lib(Long user_id, Long game_id) {
        this.user_id = user_id;
        this.game_id = game_id;
    }
}
