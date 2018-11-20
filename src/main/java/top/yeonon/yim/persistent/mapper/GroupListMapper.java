package top.yeonon.yim.persistent.mapper;

import org.apache.ibatis.annotations.Param;
import top.yeonon.yim.persistent.pojo.GroupList;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GroupListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupList record);

    int insertSelective(GroupList record);

    GroupList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupList record);

    int updateByPrimaryKey(GroupList record);

    int addMembers(@Param("groupId") long groupId, @Param("userInfo") Map<Long, String> userInfo);

    List<GroupList> selectGroupListByGroupId(@Param("groupId") long groupId);

    int checkUserInGroup(@Param("userId") long userId, @Param("groupId") long groupId);


    int deleteByUserIdAndGroupId(@Param("userId") long userId, @Param("groupId") long groupId);

    int selectCountByGroupId(@Param("groupId") long groupId);
}