package com.music.ori_sound_backend.models;

import java.util.Map;

public class Song {
    private String name;
    private String singer;
    private String publicId;

    // Constructors
    public Song(String name, String singer, String publicId) {
        this.name = name;
        this.singer = singer;
        this.publicId = publicId;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }
}
