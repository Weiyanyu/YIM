package top.yeonon.yim.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 19:55
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Session {

    private long userId;

    private String username;
}
