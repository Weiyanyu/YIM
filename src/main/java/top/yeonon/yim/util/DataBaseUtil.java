package top.yeonon.yim.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author yeonon
 * @date 2018/11/17 0017 16:08
 **/
public final class DataBaseUtil {

    private static SqlSessionFactory SQL_SESSION_FACTORY = null;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            System.err.println("找不到对应的配置文件，请确保" + resource + "文件存在与类路径下");
        }
    }

    public static SqlSession getSqlSession() {
        return SQL_SESSION_FACTORY.openSession();
    }



}
