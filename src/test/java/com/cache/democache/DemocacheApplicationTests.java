package com.cache.democache;

import com.cache.democache.bean.employee;
import com.cache.democache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemocacheApplicationTests {
@Autowired
    EmployeeMapper employeeMapper;

@Autowired
  private StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串

    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象

    @Autowired
    RedisTemplate<Object, employee> redisTemplate1;

    @Test
    public  void test1(){

//        stringRedisTemplate.opsForValue().set("abc","测试");
        stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist","2");

    }
    //测试保存对象
    @Test
    public void  test2(){
        employee emp=employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存在redis中
//        redisTemplate.opsForValue().set("emp1",emp);
        //1、将数据以json格式转化
        //2、redistemplate默认的序列化规则
        redisTemplate1.opsForValue().set("emp1",emp);

    }
    @Test
    public void contextLoads() {
        employee emp=employeeMapper.getEmpById(1);
       System.out.println(emp);
    }

}
