package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.UserDetails;
import com.cg.repository.ILoginRepository;

@Service
public class LoginService {
        @Autowired
        ILoginRepository repo;
        
       public void saveUser(UserDetails user) {
    	   repo.save(user);
       }
      
       public UserDetails checkUser(String user) {
    	    return repo.checkUser(user);
       }
}
