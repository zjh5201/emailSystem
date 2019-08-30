package com.email.mapper;

import com.email.po.Friend;
import com.email.po.FriendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendMapper {
    int countByExample(FriendExample example);

    int deleteByExample(FriendExample example);

    int insert(Friend record);

    int insertSelective(Friend record);

    List<Friend> selectByExample(FriendExample example);

    int updateByExampleSelective(@Param("record") Friend record, @Param("example") FriendExample example);

    int updateByExample(@Param("record") Friend record, @Param("example") FriendExample example);
}