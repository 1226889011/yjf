package com.cache.democache.mapper;

import com.cache.democache.bean.department;
import org.apache.ibatis.annotations.*;


//@Mapper//指定这是一个操作数据库的mapper
public interface DepartmentMapper {


    @Select("select * from t_user1 where id=#{id}")
    public department getDeptById(Integer id);

    @Delete("delete from t_user1 where id=#{id}")
    public int DeleteById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")//是不是使用自动生成的主键，告诉mybatis哪个属性是来封装主键的，插入完成以后主键又会重新封装进去，就不会出现id：null的情况了
    @Insert("insert into t_user1 (department_name) values (#{departmentName})")
    public int InsertDept(department department);

    @Update("update t_user1 set department_name=#{departmentName} where id=#{id}")
    public  int UpdateDept(department department);
}
