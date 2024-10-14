//package com.music.ori_sound_backend.service;
//
//import java.util.Map;
//
//import org.springframework.web.multipart.MultipartFile;
//
//
//public interface CloudinarySongService {
//
//	 Map upload(MultipartFile file) ;
//	 
//	 Map getSongFile(String publicId);
//}

package com.music.ori_sound_backend.service;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

import com.music.ori_sound_backend.models.Song;

public interface CloudinarySongService {
    Map<String,String> upload(MultipartFile file,String songName, String singerName);
    Map<String, String> getSongFile(String publicId);
	List<Song> getAllSongs();
	void saveSong(Song song);
}

