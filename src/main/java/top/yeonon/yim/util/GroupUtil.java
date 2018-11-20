package top.yeonon.yim.util;

import io.netty.channel.group.ChannelGroup;
import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.common.Session;
import top.yeonon.yim.persistent.mapper.GroupListMapper;
import top.yeonon.yim.persistent.mapper.GroupMapper;
import top.yeonon.yim.persistent.pojo.Group;
import top.yeonon.yim.persistent.pojo.GroupList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 群组的CRUD操作
 * @Author yeonon
 * @date 2018/11/16 0016 13:34
 **/
public final class GroupUtil {

    /**
     * 创建群组
     * @param groupName
     * @param userInfo
     * @return
     */
    public static long createGroup(String groupName, Map<Long, String> userInfo) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()){
            //创建一个群组
            GroupMapper groupMapper = sqlSession.getMapper(GroupMapper.class);
            Group group = new Group();
            group.setGroupName(groupName);
            groupMapper.insert(group);

            //往群组里加入成员
            GroupListMapper groupListMapper = sqlSession.getMapper(GroupListMapper.class);
            long groupId = group.getId();
            groupListMapper.addMembers(groupId, userInfo);

            sqlSession.commit();

            return group.getId();
        }
    }

    /**
     * 根据groupId获取群组Group实例
     * @param groupId
     * @return
     */
    public static Group getGroup(long groupId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()){
            GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
            return mapper.selectByPrimaryKey(groupId);
        }
    }

    /**
     * 根据UserId和groupId检查用户是否在群组里
     * @param userId
     * @param groupId
     * @return
     */
    public static boolean checkGroup(long userId, long groupId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()){
            GroupListMapper mapper = sqlSession.getMapper(GroupListMapper.class);
            return mapper.checkUserInGroup(userId, groupId) > 0;
        }
    }

    /**
     * 获取群组的成员基本信息
     * @param groupId
     * @return
     */
    public static List<GroupList> getGroupMember(long groupId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()) {
            GroupListMapper mapper = sqlSession.getMapper(GroupListMapper.class);
            return mapper.selectGroupListByGroupId(groupId);
        }
    }

    /**
     * 加入群组
     * @param groupId
     * @param session
     */
    public static void joinGroup(long groupId, Session session) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()){
            GroupListMapper mapper = sqlSession.getMapper(GroupListMapper.class);

            GroupList groupList = new GroupList();
            groupList.setUserId(session.getUserId());
            groupList.setUsername(session.getUsername());
            groupList.setGroupId(groupId);

            mapper.insert(groupList);

            sqlSession.commit();
        }
    }

    /**
     * 退出群组
     * @param userId
     * @param groupId
     */
    public static void quiteGroup(long userId, long groupId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()){
            GroupListMapper mapper = sqlSession.getMapper(GroupListMapper.class);
            mapper.deleteByUserIdAndGroupId(userId, groupId);
            sqlSession.commit();
        }
    }

    public static int getGroupMemberCount(long groupId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()){
            GroupListMapper mapper = sqlSession.getMapper(GroupListMapper.class);
            return mapper.selectCountByGroupId(groupId);
        }
    }

    /**
     * 删除群组
     * @param groupId
     */
    public static void deleteGroup(long groupId) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()){
            GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
            mapper.deleteByPrimaryKey(groupId);
            sqlSession.commit();
        }
    }
}
