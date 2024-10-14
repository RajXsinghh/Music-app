package com.music.ori_sound_backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.music.ori_sound_backend.models.Song;

@Service
public class CloudinarySongServiceImpl implements CloudinarySongService {

    @Autowired
    private Cloudinary cloudinary;

    // In-memory list to store songs
    private List<Song> songList = new ArrayList<>();

    @Override
    public Map<String,String> upload(MultipartFile file, String songName, String singerName) {
        try {
            // Upload the file to Cloudinary
            Map<String,String> data = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String publicId = (String) data.get("public_id");

            // Automatically extract song name from the original file name if not provided
            if (songName == null || songName.isEmpty()) {
                songName = extractSongName(file);
            }

            // Automatically set a default singer name if not provided
            if (singerName == null || singerName.isEmpty()) {
                singerName = "Unknown Artist"; // Default value
            }

            // Add the song details to the in-memory list
            songList.add(new Song(songName, singerName, publicId));

            System.out.println("File uploaded: " + file.getOriginalFilename());
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Song uploading failed!");
        }
    }

    @Override
    public List<Song> getAllSongs() {
        return songList; // Return the list of uploaded songs
    }

    @Override
    public Map<String, String> getSongFile(String publicId) {
        try {
            Map<String, String> songFile = this.cloudinary.api().resource(publicId, ObjectUtils.asMap("resource_type", "video"));
            String songUrl = (String) songFile.get("secure_url");
            System.out.println("Retrieved song URL: " + songUrl);
            return songFile;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve song!");
        }
    }

    @Override
    public void saveSong(Song song) {
        // Implementation to save song to database or another storage system (if needed)
    }

    /**
     * Helper method to extract song name from file name.
     * @param file - the uploaded MultipartFile
     * @return - extracted song name
     */
    private String extractSongName(MultipartFile file) {
        // Get original filename without extension
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            return originalFilename.replaceFirst("[.][^.]+$", ""); // Remove file extension
        }
        return "Unknown Song"; // Default value if extraction fails
    }
}
