package com.email.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.mapper.UserMapper;
import com.email.po.User;
import com.email.po.UserExample;
import com.email.po.UserExample.Criteria;
import com.email.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	//检查该账户是否存在
	public boolean checkItExist(String userid) {
		User u = userMapper.selectByPrimaryKey(userid);
		if(u!=null)
			return false;
		return true;
	}

	@Override
	public void addUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public User getUserByUserid(String userid) {
		User user = userMapper.selectByPrimaryKey(userid);
		return user;
	}

	@Override
	public void editPassword(String userid, String password) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		User user = new User();
		user.setPassword(password);
		userMapper.updateByExampleSelective(user, example);
	}

	@Override
	public void editInfo(User user) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(user.getUserid());
		userMapper.updateByExampleSelective(user, example);
	}

}
