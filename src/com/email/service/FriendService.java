package com.email.service;

import java.util.List;

import com.email.po.Friend;

public interface FriendService {

	List<Friend> getAllFriends(String userid);

	void add(Friend friend);

	void delete(String friendId);

}
