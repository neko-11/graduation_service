package cn.edu.ahut.Service;

import cn.edu.ahut.utils.Result;

/**
 * Created by zhushuangfei on 2018/3/20
 */
public interface DepartmentService {

    Result listAll();

    Result getDepartmentById(int id);

    Result saveDepartment(String departmentName, String departmentCode);

    Result updateDepartment(int id, String departmentName, String departmentCode);

    Result deleteDepartment(int id);
}
