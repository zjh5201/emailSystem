package com.email.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/*
 * 这是日期格式转换器，转换成能看得懂的格式
 * 
 * */

public class CustomDateConverter implements Converter<String,Date>{

	@Override
	public Date convert(String source) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		try {
			//转成直接返回
			return sdf.parse(source);
		} catch (ParseException e) {//转不成第一个格式转第二个
			try {
				return sdf1.parse(source);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

}
