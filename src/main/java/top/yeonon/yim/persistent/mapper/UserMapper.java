package top.yeonon.yim.persistent.mapper;

import org.apache.ibatis.annotations.Param;
import top.yeonon.yim.persistent.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}