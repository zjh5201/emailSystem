package com.email.mapper;

import com.email.po.Email;
import com.email.po.EmailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailMapper {
    int countByExample(EmailExample example);

    int deleteByExample(EmailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Email record);

    int insertSelective(Email record);

    List<Email> selectByExample(EmailExample example);

    Email selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Email record, @Param("example") EmailExample example);

    int updateByExample(@Param("record") Email record, @Param("example") EmailExample example);

    int updateByPrimaryKeySelective(Email record);

    int updateByPrimaryKey(Email record);
    
    List<Email> selectByOptions(@Param("param1") String addresseeId,@Param("param2") String title,@Param("param3") int readed,@Param("param4") String userid);
}