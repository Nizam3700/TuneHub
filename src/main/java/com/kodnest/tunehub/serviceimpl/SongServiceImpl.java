package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.SongRepository;
import com.kodnest.tunehub.service.SongService;

@Service
public class SongServiceImpl implements SongService{

	
	@Autowired
	SongRepository songRepo;
	
	
	public String addSong(Song song) {
		// TODO Auto-generated method stub
		songRepo.save(song);
		return "song added";
	}
	
	
	//this is one way to check the song is present or not
	
//	public boolean songExists(String name) {
//		// TODO Auto-generated method stub
//		        if(songRepo.findByName(name)!=null) {
//		        	return true;
//		        }
//		        else {
//		        	return false;
//		        }
//	
//	}
	
	
	//this is second way to check the song is present or not
	public boolean songExists(String name) {
		// TODO Auto-generated method stub
		Song song=songRepo.findByName(name);
		        if(song==null) {
		        	return false;
		        }
		        else {
		        	return true;
		        }
	
	}


	public List<Song> fetchAllSongs() {
		// TODO Auto-generated method stub
		
		List<Song> songs=songRepo.findAll();
		return songs;
	}


	@Override
	public void updateSong(Song s) {
		// TODO Auto-generated method stub
		songRepo.save(s);
		
	}

}
