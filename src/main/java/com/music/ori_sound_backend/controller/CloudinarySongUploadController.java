//package com.music.ori_sound_backend.controller;
//
//import java.util.List;
//import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.music.ori_sound_backend.models.Song;
//import com.music.ori_sound_backend.service.CloudinarySongService;
//
//@Controller
//@RequestMapping("/cloudinary")
//public class CloudinarySongUploadController {
//
//    @Autowired
//    private CloudinarySongService cloudinarySongService;
//
////    @PostMapping("/upload")
////    public ResponseEntity<Map> uploadSong(
////    		@RequestParam("song") MultipartFile file) {
////        Map data = this.cloudinarySongService.upload(file);
////        return new ResponseEntity<>(data, HttpStatus.OK);
////    }
//    
//    @PostMapping("/upload") 
//    public String uploadSong(
//    		@RequestParam("song") MultipartFile file,
//    		@RequestParam("singer") String singer,
//    		RedirectAttributes redirectAttributes) {
//    	
//    	Map<String,String> data = this.cloudinarySongService.upload(file, singer, singer);
//    	
//    	String publicId = data.get("public_id");
//    	String songName = data.get("original_filename");
//    	
//    	Song song = new Song(songName, singer, publicId);
//    	cloudinarySongService.saveSong(song);
//    	
//    	redirectAttributes.addFlashAttribute("message", "Song uploaded successfully!");
//    	return "redirect:/cloudinary/";
//    }
//    
//    
//    
//    
//
//    @GetMapping("/")
//    public String getHomepage(Model model) {
//        List<Song> songs = cloudinarySongService.getAllSongs(); // Get all uploaded songs
//        model.addAttribute("songs", songs);
//        return "index"; // Name of your Thymeleaf template
//    }
//    
//    
//}


package com.music.ori_sound_backend.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.music.ori_sound_backend.models.Song;
import com.music.ori_sound_backend.service.CloudinarySongService;

@Controller
@RequestMapping("/cloudinary")
public class CloudinarySongUploadController {

    @Autowired
    private CloudinarySongService cloudinarySongService;

    @PostMapping("/upload") 
    public String uploadSong(
            @RequestParam("song") MultipartFile file,
            @RequestParam(value = "singer", required = false) String singer, // Optional singer parameter
            RedirectAttributes redirectAttributes) {
        
        // Upload the song and retrieve the data
        Map<String,String> data = this.cloudinarySongService.upload(file, null, singer);
        
        // Get the public ID and song name
        String publicId = data.get("public_id");
        String songName = data.get("original_filename") != null ? data.get("original_filename").replaceFirst("[.][^.]+$", "") : "Unknown Song";

        // Create and save the song object
        Song song = new Song(songName, singer != null ? singer : "Unknown Artist", publicId);
        cloudinarySongService.saveSong(song);
        
        redirectAttributes.addFlashAttribute("message", "Song uploaded successfully!");
        return "redirect:/cloudinary/"; // Redirect to the homepage
    }

    @GetMapping("/")
    public String getHomepage(Model model) {
        List<Song> songs = cloudinarySongService.getAllSongs(); // Get all uploaded songs
        model.addAttribute("songs", songs);
        return "index"; // Name of your Thymeleaf template
    }
}
