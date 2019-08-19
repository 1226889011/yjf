package com.cache.democache.mapper;


//@Mapper或者@MapperScan将接口扫描装配到容器中

import com.cache.democache.bean.department;
import com.cache.democache.bean.employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmployeeMapper {
    @Select("select * from t_user where user_id=#{user_id}")
    public employee getEmpById(Integer id);

    @Select("select * from t_user where user_name=#{user_name}")
    public List<employee> getEmpByName(String user_name);

    @Delete("delete from t_user where user_id=#{user_id}")
    public void DeleteEmpById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "user_id")//是不是使用自动生成的主键，告诉mybatis哪个属性是来封装主键的，插入完成以后主键又会重新封装进去，就不会出现id：null的情况了
    @Insert("insert into t_user (user_name,user_password,phone) values (#{user_name},#{user_password},#{phone})")
    public void InsertEmp(employee employee);

    @Update("update t_user set user_name=#{user_name},user_password=#{user_password},phone=#{phone} where user_id=#{user_id}")
    public  void UpdateEmp(employee employee);

}
