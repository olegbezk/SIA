package com.sia.tutorial.data;

import com.sia.tutorial.User;

public interface UserRepository {
	
	User save(User user);
	  
	User findByUsername(String username);

}
