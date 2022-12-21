package com.revature.service;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;

public class UserService {

	private UserDao dao;

	public UserService(){
		this.dao = new UserDao();
	}

	public User getUserByUsername(String username) {
		/*
		 *serice methos needs to do is return the data grabbed by the dao object. 
		   thats it: the other parts of the app will handle interpreting what to do
		   with the user info
		 */
		return this.dao.getUserByUsername(username);
	}

	public User register(UsernamePasswordAuthentication registerRequest) {
		return this.dao.createUser(registerRequest);
	}
}
