package top.yeonon.yim;

import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.mapper.FriendListMapper;
import top.yeonon.yim.pojo.FriendList;
import top.yeonon.yim.util.DataBaseUtil;

/**
 * @Author yeonon
 * @date 2018/11/18 0018 15:18
 **/
public class Main {

    public static void main(String[] args) {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        FriendListMapper mapper = sqlSession.getMapper(FriendListMapper.class);
        FriendList friendList = new FriendList();
        friendList.setFriendId(1L);
        friendList.setUserId(3L);
        friendList.setFriendUsername("yeonon");
        mapper.insert(friendList);
        sqlSession.commit();
    }
}
