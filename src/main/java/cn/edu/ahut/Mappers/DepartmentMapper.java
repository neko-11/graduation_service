package cn.edu.ahut.Mappers;

import cn.edu.ahut.Entity.Department;
import tk.mybatis.mapper.common.Mapper;

public interface DepartmentMapper extends Mapper<Department> {

    int getIdByName(String departmentName);
}