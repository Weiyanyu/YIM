package top.yeonon.yim;

import org.apache.ibatis.session.SqlSession;
import top.yeonon.yim.mapper.FriendListMapper;
import top.yeonon.yim.pojo.FriendList;
import top.yeonon.yim.util.DataBaseUtil;

import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/11/18 0018 15:18
 **/
public class Main {

    public static void main(String[] args) {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        FriendListMapper mapper = sqlSession.getMapper(FriendListMapper.class);
        Set<Long> ids = mapper.selectFriendIdsByUserId(1L);
        ids.forEach(System.out::println);
    }
}
