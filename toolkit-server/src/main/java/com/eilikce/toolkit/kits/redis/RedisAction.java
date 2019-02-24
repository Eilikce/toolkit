package com.eilikce.toolkit.kits.redis;

import com.eilikce.toolkit.action.BaseAction;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;

public class RedisAction implements BaseAction<String> {

    private RedisTemplate<String, String> template;

    @Override
    public BaseAction init(Object... objects) {
        template = (RedisTemplate<String, String>) objects[0];
        return this;
    }

    @Override
    public String doAction() {
        String key = "toolkit-test";

        ValueOperations<String, String> valueOperations = template.opsForValue();

        //插入当前时间
        Date date = new Date();

        valueOperations.set(key, date.toString());

        //读取
        String value =  valueOperations.get(key);

        return value;
    }
}