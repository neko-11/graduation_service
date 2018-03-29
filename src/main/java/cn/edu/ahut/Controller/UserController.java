package cn.edu.ahut.Controller;

import cn.edu.ahut.Entity.Record;
import cn.edu.ahut.Service.UserService;
import cn.edu.ahut.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhushuangfei on 2018/3/19
 */
@Api("user表相关API")
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/cn/edu/ahut/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(Record.class);

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取所有用户")
    @RequestMapping(value = "/listAllUser", method = RequestMethod.GET)
    public Result listAllUser() {
        Result result = userService.listAllUser();
        logger.info("-listAllUser：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据id查找用户")
    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    public Result getUserById(@RequestParam("id") int id) {
        Result result = userService.getUserById(id);
        logger.info("-getUserById：" + result.toString());
        return result;
    }

    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public Result saveUser(@RequestParam("userName") String userName, @RequestParam("userCode") String userCode, @RequestParam("departmentName") String departmentName, @RequestParam("role") String role) {
        Result result = userService.saveUser(userName, userCode, departmentName, role);
        logger.info("-saveUser：" + result.toString());
        return result;
    }

    @ApiOperation(value = "更新用户头像")
    @RequestMapping(value = "/updateImage", method = RequestMethod.POST)
    public Result updateImage(@RequestParam("id") int id, @RequestParam("image") MultipartFile image) {
        Result result = userService.updateImage(id, image);
        logger.info("-updateImage：" + result.toString());
        return result;
    }

    @ApiOperation(value = "更新用户信息")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Result updateUser(@RequestParam("id") int id, @RequestParam("userName") String userName, @RequestParam("userCode") String userCode, @RequestParam("departmentName") String departmentName, @RequestParam("role") String role) {
        Result result = userService.updateUser(id, userName, userCode, departmentName, role);
        logger.info("-updateUser：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.DELETE)
    public Result deleteUserById(@RequestParam("id") int id) {
        Result result = userService.deleteUserById(id);
        logger.info("-deleteUserById：" + result.toString());
        return result;
    }

    @ApiOperation(value = "登陆检查")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam("image") String image) {
        Result result = userService.login(image);
        logger.info("-login：" + result.toString());
        return result;
    }
}
