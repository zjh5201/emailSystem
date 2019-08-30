package com.email.service;

import java.util.List;

import com.email.po.Email;

public interface EmailService {

	void addMail(Email email);

	int getTheLastId();

	List<Email> selectByErId(String userid);

	Email getEmailById(int id);

	List<Email> selectByOptions(Email email,String userid);

	List<Email> selectByEeId(String string);

	void sedelete(int id);

	void reDelete(int id);

	void setEmailReaded(int id);

	int getCountsNoReaded(String userid);

	void listDelete(List<Integer> list);

	void seListDelete(List<Integer> list);
	
}
