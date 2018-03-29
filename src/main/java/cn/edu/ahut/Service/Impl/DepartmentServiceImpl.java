package cn.edu.ahut.Service.Impl;

import cn.edu.ahut.Entity.Department;
import cn.edu.ahut.Mappers.DepartmentMapper;
import cn.edu.ahut.Service.DepartmentService;
import cn.edu.ahut.utils.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhushuangfei on 2018/3/20
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public Result listAll() {
        Result result = new Result();
        List<Department> list = departmentMapper.selectAll();
        result.setCode(0);
        result.setMessage("No Error");
        result.setResult(JSONArray.fromObject(list));
        return result;
    }

    @Override
    public Result getDepartmentById(int id) {
        Result result = new Result();
        Department department = departmentMapper.selectByPrimaryKey(id);
        result.setCode(0);
        result.setMessage("No Error");
        result.setResult(JSONObject.fromObject(department));
        return result;
    }

    @Override
    public Result saveDepartment(String departmentName, String departmentCode) {
        Result result = new Result();
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setDepartmentCode(departmentCode);
        int num = departmentMapper.insert(department);
        if (num == 1) {
            result.setCode(0);
            result.setMessage("No Error");
            return result;
        }
        result.setCode(1);
        result.setMessage("Error");
        return result;
    }

    @Override
    public Result updateDepartment(int id, String departmentName, String departmentCode) {
        Result result = new Result();
        Department department = new Department();
        department.setId(id);
        department.setDepartmentName(departmentName);
        department.setDepartmentCode(departmentCode);
        int num = departmentMapper.updateByPrimaryKey(department);
        if (num == 1) {
            result.setCode(0);
            result.setMessage("No Error");
            return result;
        }
        result.setCode(1);
        result.setMessage("Error");
        return result;
    }

    @Override
    public Result deleteDepartment(int id) {
        Result result = new Result();
        int num = departmentMapper.deleteByPrimaryKey(id);
        if (num == 1) {
            result.setMessage("No Error");
            result.setCode(0);
            return result;
        }
        result.setCode(1);
        result.setMessage("Error");
        return result;
    }
}
