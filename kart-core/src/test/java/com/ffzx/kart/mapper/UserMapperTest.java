package com.ffzx.kart.mapper;

import com.ffzx.kart.TestBase;
import com.ffzx.kart.model.User;
import com.ffzx.kart.util.JsonConverter;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */
public class UserMapperTest extends TestBase {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelectByExample(){
        List<User> list= userMapper.selectByExample(null);
        logger.info(JsonConverter.toJson(list));
    }

}
