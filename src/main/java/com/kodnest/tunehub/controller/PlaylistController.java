package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.PlaylistService;
import com.kodnest.tunehub.service.SongService;

@Controller
public class PlaylistController {

	
	
	@Autowired
	SongService songService;
	
	@Autowired
	PlaylistService playlistService;  
	
	
	@GetMapping("/createplaylists")
	public String createPlaylists(Model model) {
		List<Song> songList=songService.fetchAllSongs();
		//System.out.println(songList);
		model.addAttribute("songs", songList);
		return "createplaylists";
	}
	
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist){
		
		//updating the flaylist table
		playlistService.addplaylist(playlist);	
		
		//updating the song table
		List<Song> songList=playlist.getSongs();
	     for(Song s : songList) {
	    	 s.getPlaylist().add(playlist);
	    	 songService.updateSong(s);
	     }
		return "adminhome";
	}
	

	@GetMapping("/viewplaylists")
	public String viewplaylists(Model model) {
		List<Playlist> allplaylist=playlistService.fetchAllPlaylists();
		model.addAttribute("allplaylist",allplaylist);
		return "displayplaylist";
		
	}

	
	
}
