package com.email.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.mapper.EmailMapper;
import com.email.po.Email;
import com.email.po.EmailExample;
import com.email.po.EmailExample.Criteria;
import com.email.service.EmailService;

@Service("emailService")
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private EmailMapper emailMapper;

	@Override
	public void addMail(Email email) {
		//插入记录
		emailMapper.insert(email);
	}

	@Override
	//获取最新应该插入的id
	public int getTheLastId() {
		EmailExample example = new EmailExample();
		List<Email> list =  emailMapper.selectByExample(example);
		int max = 0;
		for (Email email : list) {
			if(email.getId()>max) {
				max = email.getId();
			}
		}
		return max+1;
	}

	@Override
	//根据收件人Id来查找Email集合。
	public List<Email> selectByErId(String userid) {
		EmailExample example = new EmailExample();
		Criteria criteria = example.createCriteria();
		//设置条件
		criteria.andAddresserIdEqualTo(userid);
		List<Email> list = emailMapper.selectByExample(example);
		return list;
	}

	@Override
	//根据邮件编号来获取Email实例并返回
	public Email getEmailById(int id) {
		Email email = emailMapper.selectByPrimaryKey(id);
		return email;
	}

	@Override
	public List<Email> selectByOptions(Email email,String userid) {
		List<Email> list = emailMapper.selectByOptions(email.getAddresseeId(),email.getTitle(),email.getReaded(),userid);
		return list;
	}

	@Override
	public List<Email> selectByEeId(String userid) {
		EmailExample example = new EmailExample();
		Criteria criteria = example.createCriteria();
		//设置条件
		criteria.andAddresseeIdEqualTo(userid);
		List<Email> list = emailMapper.selectByExample(example);
		return list;
	}

	@Override
	public void sedelete(int id) {
		// TODO Auto-generated method stub
		Email email = emailMapper.selectByPrimaryKey(id);
		int readed = email.getReaded();
		EmailExample example = new EmailExample();
		Criteria criteria = example.createCriteria();
		if(readed==2) {
			emailMapper.deleteByPrimaryKey(id);
		}else if(readed==0){
			Email e = new Email();
			e.setReaded(3);
			criteria.andIdEqualTo(id);
			emailMapper.updateByExampleSelective(e, example);
		}else {
			Email e = new Email();
			e.setReaded(4);
			criteria.andIdEqualTo(id);
			emailMapper.updateByExampleSelective(e, example);
		}
		
	}

	@Override
	public void reDelete(int id) {
		Email email = emailMapper.selectByPrimaryKey(id);
		int readed = email.getReaded();
		EmailExample example = new EmailExample();
		Criteria criteria = example.createCriteria();
		if(readed == 0||readed == 1) {
			Email e = new Email();
			e.setReaded(2);
			criteria.andIdEqualTo(id);
			emailMapper.updateByExampleSelective(e, example);
		}
		if(readed == 3||readed == 4) {
			emailMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void setEmailReaded(int id) {
		// TODO Auto-generated method stub
		Email email = emailMapper.selectByPrimaryKey(id);
		System.out.println(id);
		int readed = email.getReaded();
		EmailExample example = new EmailExample();
		Criteria criteria = example.createCriteria();
		if(readed==0) {
			Email e = new Email();
			e.setReaded(1);
			criteria.andIdEqualTo(id);
			emailMapper.updateByExampleSelective(e, example);
			System.out.println(1);
		}else {
			Email e = new Email();
			e.setReaded(4);
			criteria.andIdEqualTo(id);
			emailMapper.updateByExampleSelective(e, example);
			System.out.println(2);
		}
	}

	@Override
	public int getCountsNoReaded(String userid) {
		EmailExample example = new EmailExample();
		Criteria criteria = example.createCriteria();
		criteria.andAddresserIdEqualTo(userid);
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(3);
		criteria.andReadedIn(list);
		List<Email> allList = emailMapper.selectByExample(example);
		return allList.size();
	}

	@Override
	public void listDelete(List<Integer> list) {
		for (Integer id : list) {
			reDelete(id);
		}
	}

	@Override
	public void seListDelete(List<Integer> list) {
		// TODO Auto-generated method stub
		for (Integer id : list) {
			sedelete(id);
		}
	}
	
}
