package com.email.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.mapper.FriendMapper;
import com.email.po.Friend;
import com.email.po.FriendExample;
import com.email.po.FriendExample.Criteria;
import com.email.service.FriendService;

@Service("friendService")
public class FriendServiceImpl implements FriendService{

	@Autowired
	private FriendMapper friendMapper;
	
	public List<Friend> getAllFriends(String userid) {
		FriendExample example = new FriendExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		List<Friend> list = friendMapper.selectByExample(example);
		return list;
	}

	@Override
	public void add(Friend friend) {
		// TODO Auto-generated method stub
		friendMapper.insert(friend);
	}

	@Override
	public void delete(String friendId) {
		FriendExample example = new FriendExample();
		Criteria criteria = example.createCriteria();
		criteria.andFriendIdEqualTo(friendId);
		friendMapper.deleteByExample(example);
	}

}
