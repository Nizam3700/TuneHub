package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.repository.PlaylistRepository;
import com.kodnest.tunehub.service.PlaylistService;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	
	@Autowired
	PlaylistRepository playlistRepo;

	@Override
	public void addplaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		playlistRepo.save(playlist);
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
		List<Playlist> allplaylist=playlistRepo.findAll();
		return allplaylist;
	}
}
