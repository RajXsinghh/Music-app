package com.music.ori_sound_backend.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class ProjectConfig {
	
	@Bean
    public Cloudinary getCloudinary() {
    	Map config=new HashMap();
    	config.put("cloud_name","dvs8icta4");
    	config.put("api_key","941933723721268");
    	config.put("api_secret","kfPtkFUsWYVIjPFBQvDnB2npHuU");
    	config.put("secure",true);
    	
    	return new Cloudinary(config); 
    	  
    	
    	
    }
}   
