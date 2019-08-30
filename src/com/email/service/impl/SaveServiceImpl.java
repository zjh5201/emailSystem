package com.email.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.mapper.SaveMapper;
import com.email.po.Save;
import com.email.po.SaveExample;
import com.email.po.SaveExample.Criteria;
import com.email.service.SaveService;

@Service("saveService")
public class SaveServiceImpl implements SaveService{

	@Autowired
	private SaveMapper saveMapper;
	
	@Override
	public void addSave(Save save) {
		saveMapper.insert(save);
	}

	@Override
	public int getTheLastId() {
		SaveExample example = new SaveExample();
		List<Save> list =  saveMapper.selectByExample(example);
		int max = 0;
		for (Save Save : list) {
			if(Save.getId()>max) {
				max = Save.getId();
			}
		}
		return max+1;
	}

	@Override
	public List<Save> selectByEE(String string) {
		SaveExample example = new SaveExample();
		Criteria criteria = example.createCriteria();
		criteria.andAddresseeIdEqualTo(string);
		List<Save> list = saveMapper.selectByExample(example);
		return list;
	}

	@Override
	public Save selectBySaveId(int id) {
		Save save = saveMapper.selectByPrimaryKey(id);
		return save;
	}

}
