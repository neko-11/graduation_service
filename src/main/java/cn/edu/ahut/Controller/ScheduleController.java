package cn.edu.ahut.Controller;

import cn.edu.ahut.Entity.Record;
import cn.edu.ahut.Service.ScheduleService;
import cn.edu.ahut.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by zhushuangfei on 2018/3/19
 */
@Api("schedule相关API")
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/cn/edu/ahut/schedule")
public class ScheduleController {

    private static Logger logger = LoggerFactory.getLogger(Record.class);

    @Autowired
    ScheduleService scheduleService;

    @ApiOperation(value = "获取所有计划时间")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public Result listAllUser() {
        Result result = scheduleService.listAll();
        logger.info("-listAll：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据id查找计划时间")
    @RequestMapping(value = "/getScheduleById", method = RequestMethod.POST)
    public Result getUserById(@RequestParam("id") int id) {
        Result result = scheduleService.getScheduleById(id);
        logger.info("-getScheduleById：" + result.toString());
        return result;
    }

    @ApiOperation(value = "添加计划时间")
    @RequestMapping(value = "/saveSchedule", method = RequestMethod.POST)
    public Result saveUser(@RequestParam("arriveTime") Date arriveTime, @RequestParam("leaveTime") Date leaveTime) {
        Result result = scheduleService.saveSchedule(arriveTime, leaveTime);
        logger.info("-saveSchedule：" + result.toString());
        return result;
    }

    @ApiOperation(value = "更新计划时间")
    @RequestMapping(value = "/updateSchedule", method = RequestMethod.POST)
    public Result updateUser(@RequestParam("id") int id, @RequestParam("arriveTime") Date arriveTime, @RequestParam("leaveTime") Date leaveTime) {
        Result result = scheduleService.updateSchedule(id, arriveTime, leaveTime);
        logger.info("-updateSchedule：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除计划时间")
    @RequestMapping(value = "/deleteSchedule", method = RequestMethod.DELETE)
    public Result deleteUserById(@RequestParam("id") int id) {
        Result result = scheduleService.deleteSchedule(id);
        logger.info("-deleteSchedule：" + result.toString());
        return result;
    }
}
