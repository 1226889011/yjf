package com.cache.democache.controller;

import com.cache.democache.bean.department;
import com.cache.democache.bean.employee;
import com.cache.democache.mapper.EmployeeMapper;
import com.cache.democache.service.DepartmentService;
import com.cache.democache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/dep/{id}")
    public department getDepartment(@PathVariable("id") Integer id){

        return  departmentService.DepartmentById(id);
    }

    @GetMapping("/emps/{id}")
    public employee getEmplyee(@PathVariable("id") Integer id){
        employee employee=employeeService.getEmp(id);
        return  employee;
    }
    @GetMapping("/emps")
    public  employee upEmp(employee employee){
        employee employee1=employeeService.upEmp(employee);
        return  employee1;
    }
    @GetMapping("de/{id}")
    public String dele1Emp(@PathVariable("id") Integer integer){
       employeeService.deleEmp(integer);
        return "success";
    }
    @GetMapping("name/{name}")
    public employee GetByName(@PathVariable("name") String name){

        return   employeeService.getEmpName(name);
    }

}
