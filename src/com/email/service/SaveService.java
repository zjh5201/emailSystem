package com.email.service;

import java.util.List;

import com.email.po.Email;
import com.email.po.Save;

public interface SaveService {
	public void addSave(Save save);

	public int getTheLastId();

	public List<Save> selectByEE(String string);

	public Save selectBySaveId(int id);
}
