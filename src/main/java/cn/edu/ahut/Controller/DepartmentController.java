package cn.edu.ahut.Controller;

import cn.edu.ahut.Entity.Record;
import cn.edu.ahut.Service.DepartmentService;
import cn.edu.ahut.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhushuangfei on 2018/3/20
 */

@Api("department相关API")
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/cn/edu/ahut/department")
public class DepartmentController {

    private static Logger logger = LoggerFactory.getLogger(Record.class);

    @Autowired
    DepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public Result listAllUser() {
        Result result = departmentService.listAll();
        logger.info("-listAll：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据id查找部门")
    @RequestMapping(value = "/getDepartmentById", method = RequestMethod.POST)
    public Result getUserById(@RequestParam("id") int id) {
        Result result = departmentService.getDepartmentById(id);
        logger.info("-getDepartmentById：" + result.toString());
        return result;
    }

    @ApiOperation(value = "添加部门")
    @RequestMapping(value = "/saveDepartment", method = RequestMethod.POST)
    public Result saveUser(@RequestParam("departmentName") String departmentName, @RequestParam("departmentCode") String departmentCode) {
        Result result = departmentService.saveDepartment(departmentName, departmentCode);
        logger.info("-saveDepartment：" + result.toString());
        return result;
    }

    @ApiOperation(value = "更新部门信息")
    @RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
    public Result updateUser(@RequestParam("id") int id, @RequestParam("departmentName") String departmentName, @RequestParam("departmentCode") String departmentCode) {
        Result result = departmentService.updateDepartment(id, departmentName, departmentCode);
        logger.info("-updateDepartment：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除部门")
    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.DELETE)
    public Result deleteUserById(@RequestParam("id") int id) {
        Result result = departmentService.deleteDepartment(id);
        logger.info("-deleteDepartment：" + result.toString());
        return result;
    }
}
