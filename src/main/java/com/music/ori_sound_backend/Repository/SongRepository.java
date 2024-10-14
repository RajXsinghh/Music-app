package com.music.ori_sound_backend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.music.ori_sound_backend.models.Song;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {

}
