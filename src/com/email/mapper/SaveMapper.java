package com.email.mapper;

import com.email.po.Save;
import com.email.po.SaveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaveMapper {
    int countByExample(SaveExample example);

    int deleteByExample(SaveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Save record);

    int insertSelective(Save record);

    List<Save> selectByExample(SaveExample example);

    Save selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Save record, @Param("example") SaveExample example);

    int updateByExample(@Param("record") Save record, @Param("example") SaveExample example);

    int updateByPrimaryKeySelective(Save record);

    int updateByPrimaryKey(Save record);
}