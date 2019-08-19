package com.cache.democache.service;

import com.cache.democache.bean.department;
import com.cache.democache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
@CacheConfig(cacheManager = "cacheManager")
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    CacheManager cacheManager;

//    @Cacheable(cacheNames = "dep") //key不设置默认是id
//    public department DepartmentById(Integer id){
//        System.out.println("查询部门");
//        department department = departmentMapper.getDeptById(id);
//
//        return  department;
//    }
//1、@Cacheable(cacheNames = "dep") //key不设置默认是id
public department DepartmentById(Integer id){
    System.out.println("查询部门");
    department department = departmentMapper.getDeptById(id);
    Cache dep = cacheManager.getCache("dep");//等同于1的功能，只能缓存一次
    dep.put("dep:1",department);
    return  department;
}

}
