package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimpl.SongServiceImpl;

@Controller
public class SongController {

	@Autowired
	SongServiceImpl songService;
	
	@PostMapping("/addsong")
	public String addSong(@ModelAttribute Song song) {
		               boolean songStatus=songService.songExists(song.getName());
		               if(songStatus==false) {
		            	   songService.addSong(song);
		            	   System.out.println("song added");
		               }
		               else {
		            	   System.out.println("song already added");
		               }
		
		return "adminhome";
	}
	
	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
		List<Song> songList=songService.fetchAllSongs();
		//System.out.println(songList);
		model.addAttribute("songs", songList);
		return "displaysongs";
	}
	
	
	@GetMapping("/playsongs")
	public String playsongs(Model model) {
		
		boolean premium=false;
		if(premium) {
		List<Song> songList=songService.fetchAllSongs();
		//System.out.println(songList);
		model.addAttribute("songs", songList);
		return "displaysongs";
		}
		else {
			return "subscriptionform";
		}
		
	}
	
	
}
