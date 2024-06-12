package com.kodnest.tunehub.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;
import com.kodnest.tunehub.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
    @Autowired
	UserRepository userRepo;
	
	public String addUser(User user) {
		

		userRepo.save(user);
		return "User added Successfully";
	}

	public boolean emailExists(String email) {
		
		if(userRepo.findByEmail(email)!=null){
			return true;
		}
		else {
			return false;
		}
		
	}

	public boolean validateUser(String email, String password) {
		// TODO Auto-generated method stub
		
		     User user =userRepo.findByEmail(email);
		     
		      String dbpwd=user.getPassword();
		      if(password.equals(dbpwd)) {
		    	  return true;
		      }
		      else {
		    	  return false;  
		      }
		
	}

	public String getRole(String email) {
		// TODO Auto-generated method stub
		
		User user=userRepo.findByEmail(email);
		return user.getRole();
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}
}
