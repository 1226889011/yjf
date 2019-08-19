package com.cache.democache.bean;

import java.io.Serializable;

public class department  implements Serializable {
    private Integer id;
    private  String department_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
}
