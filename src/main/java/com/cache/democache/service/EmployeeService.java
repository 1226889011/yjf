package com.cache.democache.service;

import com.cache.democache.bean.employee;
import com.cache.democache.mapper.EmployeeMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@CacheConfig(cacheNames = "emp" ,cacheManager = "cacheManager")//抽取缓存的公共配置
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

//将方法的运行结果进行缓存，以后再要相同的数据，直接从缓存中获取，不用调用方法
    //CacheManager管理多个Cache组件，兑缓存的正真CRUD操作在Cache组件中，每一个缓存组件有自己唯一一个名字
    //几个属性：cacheNames/value：指定缓存的名字；key：缓存数据使用的key，可以用来指定，默认是使用方法的值
    //如果值是1，方法的返回值就是1，编写SpEL:#id,参数id的值 #a0,#p0,#root.args[0]
    //keyGenerator:key的生成器,可以自己指定的key的生成器的组件id，key/keyGenerator二选一使用
    //cacheManage：指定缓存管理器，或者cacheResolver指定获取解析器
    //condition：指定符合条件的情况下才缓存；
    //unless:否定缓存；当unless为true，方法的返回值就就不缓存
    //sync：是否使用异步模式
    /*
    原理：
    1、自动配置类：CacheAutoConfiguration
    2、缓存的配置类（11个）
    3、哪个配置类默认生效：SimpleCacheConfiguration;
    4、给容器中注册了一个CacheManage,ConcurrentMapCacheManager
    运行流程:
    @cacheable
    1、方法运行之前，先去查询Cache（缓存组建），按照chcheNames指定的名字获取；
    （CacheNameer先获取相应的缓存）,第一次获取缓存如果没有Cache组件会自动创建。
    2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数；
    key是按照某种策略生成的；默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key；
            SimpleKeyGenerator生成key的默认策略；
            如果没有参数：key=new SimpleKey（）；
            如果有一个参数：key=参数的值
            如果有多个参数：key=new SimpleKey（params）
     3、没有查到缓存就调用目标方法；
     4、将目标方法返回结果，放入缓存中

     @Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     如果没有就运行方法并将结果放入缓存；以后再来调用就可以直接使用缓存中的数据

     核心：
     1、使用CacheManage[ConcurrentMapCacheManager]按照名字得到Cache[ConcurrentMaoCache]组件
     2、key使用keyGenerator生成的，默认是SimpleKeyGenerator

     */
    /*
    @CachePut:既调用方法，又更新缓存数据；
    修改了数据库的某个数据，同时更新缓存；
    运行时机：
    1、先调用目标方法
    2、将目标方法的结果缓存起来
     */
    @Cacheable(cacheNames = "emp")//或者写成#a0>1
    public employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        employee emp=employeeMapper.getEmpById(id);
        return emp;
    }
    @CachePut(value = "emp",key ="#employee.user_id")//或者用result.user_id，有key可以更新缓存中的这条数据
    public employee upEmp(employee employee){
        System.out.println("跟新"+employee.getUser_id()+"号员工");
        employeeMapper.UpdateEmp(employee);
        return employee;
    }
    @CacheEvict(value = "emp",key = "#id")
    public void deleEmp(Integer id){
        System.out.println("删除"+id+"号员工缓存");

    }
    @Caching(
            cacheable={
                    @Cacheable(value = "emp",key ="#name")//先查询缓存这一步，然后根据id和phone再存两条，再次查询id或phone时就不用再去访问数据库了
            },
            put = {
                    @CachePut(value = "emp",key = "#result.user_id"),//如果第二次查询name，还是会去查询数据库，因为有@CachePut注解意味着方法一定要被调用
                    @CachePut(value = "emp",key ="#result.phone")
            }
    )
    public employee getEmpName(String name){

     return employeeMapper.getEmpByName(name).get(0);


    }
}
