package top.yeonon.yim.persistent.mapper;

import top.yeonon.yim.persistent.pojo.FriendList;
import top.yeonon.yim.persistent.pojo.User;

import java.util.Set;

public interface FriendListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FriendList record);

    int insertSelective(FriendList record);

    FriendList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FriendList record);

    int updateByPrimaryKey(FriendList record);

    Set<Long> selectFriendIdsByUserId(Long userId);
}