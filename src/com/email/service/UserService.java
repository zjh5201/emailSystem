package com.email.service;

import com.email.po.User;

public interface UserService {
	public boolean checkItExist(String userid);
	
	public void addUser(User user);

	public User getUserByUserid(String string);

	public void editPassword(String userid, String password);

	public void editInfo(User user);
}
