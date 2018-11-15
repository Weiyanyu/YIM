package top.yeonon.yim.common;

import io.netty.util.AttributeKey;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 19:57
 **/
public interface Attributes {

    AttributeKey<Session> LOGIN_SESSION = AttributeKey.newInstance("login_session");

}
