package cn.edu.ahut.Controller;

import cn.edu.ahut.Entity.Record;
import cn.edu.ahut.Service.RecordService;
import cn.edu.ahut.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhushuangfei on 2018/3/13
 */
@Api("record相关API")
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/cn/edu/ahut/record")
public class RecordController {

    private static Logger logger = LoggerFactory.getLogger(Record.class);

    @Autowired
    RecordService recordService;

    @ApiOperation("签到")
    @RequestMapping(value = "/saveRecord", method = RequestMethod.POST)
    public Result saveRecord(@RequestParam("image") String image) {
        Result result = recordService.saveRecord(image);
        logger.info("-saveRecord：" + result.toString());
        return result;
    }

    @ApiOperation("获取签到记录")
    @RequestMapping(value = "/listRecord", method = RequestMethod.POST)
    public Result listRecord(@RequestParam(value = "userId", required = false) String userId, @RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "departmentName", required = false) String departmentName, @RequestParam(value = "startTime", required = false) String startTime, @RequestParam(value = "endTime", required = false) String endTime) {
        Result result = recordService.listRecord(userId, userName, departmentName, startTime, endTime);
        logger.info("-listRecord：" + result.toString());
        return result;
    }

    @ApiOperation("根据id查找签到记录")
    @RequestMapping(value = "/getRecordById", method = RequestMethod.POST)
    public Result getRecordById(@RequestParam("id") int id) {
        Result result = recordService.getRecordById(id);
        logger.info("-getRecordById：" + result.toString());
        return result;
    }

    @ApiOperation("更新签到时间")
    @RequestMapping(value = "/updateRecord", method = RequestMethod.POST)
    public Result updateRecord(@RequestParam("id") int id, @RequestParam("arriveTime") String arriveTime) {
        Result result = recordService.updateRecord(id, arriveTime);
        logger.info("-updateRecord：" + result.toString());
        return result;
    }

    @ApiOperation("删除签到记录")
    @RequestMapping(value = "/deleteRecord", method = RequestMethod.DELETE)
    public Result deleteRecord(@RequestParam("id") int id) {
        Result result = recordService.deleteRecord(id);
        logger.info("-deleteRecord：" + result.toString());
        return result;
    }
}