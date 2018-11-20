package top.yeonon.yim;

import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.persistent.mapper.FriendListMapper;
import top.yeonon.yim.persistent.mapper.GroupListMapper;
import top.yeonon.yim.persistent.mapper.GroupMapper;
import top.yeonon.yim.persistent.pojo.FriendList;
import top.yeonon.yim.persistent.pojo.Group;
import top.yeonon.yim.persistent.pojo.GroupList;
import top.yeonon.yim.util.DataBaseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/11/18 0018 15:18
 **/
public class Main {

    public static void main(String[] args) {
        try (SqlSession sqlSession = DataBaseUtil.getSqlSession()){
            GroupListMapper groupListMapper = sqlSession.getMapper(GroupListMapper.class);
            List<GroupList> lists = groupListMapper.selectGroupListByGroupId(7L);
            for (GroupList groupList : lists) {
                System.out.println(groupList);
            }
        }

    }
}
