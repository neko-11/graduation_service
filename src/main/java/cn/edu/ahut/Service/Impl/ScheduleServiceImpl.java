package cn.edu.ahut.Service.Impl;

import cn.edu.ahut.Entity.Schedule;
import cn.edu.ahut.Mappers.ScheduleMapper;
import cn.edu.ahut.Service.ScheduleService;
import cn.edu.ahut.utils.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhushuangfei on 2018/3/19
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public Result listAll() {
        Result result = new Result();
        List<Schedule> list = scheduleMapper.selectAll();
        result.setCode(0);
        result.setMessage("No Error");
        result.setResult(JSONArray.fromObject(list));
        return result;
    }

    @Override
    public Result getScheduleById(int id) {
        Result result = new Result();
        Schedule schedule = scheduleMapper.selectByPrimaryKey(id);
        result.setCode(0);
        result.setMessage("No Error");
        result.setResult(JSONObject.fromObject(schedule));
        return result;
    }

    @Override
    public Result saveSchedule(Date arriveTime, Date leaveTime) {
        Result result = new Result();
        Schedule schedule = new Schedule();
        schedule.setArriveTime(arriveTime);
        schedule.setLeaveTime(leaveTime);
        int num = scheduleMapper.insert(schedule);
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
    public Result updateSchedule(int id, Date arriveTime, Date leaveTime) {
        Result result = new Result();
        Schedule schedule = new Schedule();
        schedule.setId(id);
        schedule.setArriveTime(arriveTime);
        schedule.setLeaveTime(leaveTime);
        int num = scheduleMapper.updateByPrimaryKey(schedule);
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
    public Result deleteSchedule(int id) {
        Result result = new Result();
        int num = scheduleMapper.deleteByPrimaryKey(id);
        if (num == 1) {
            result.setCode(0);
            result.setMessage("No Error");
            return result;
        }
        result.setCode(1);
        result.setMessage("Error");
        return result;
    }
}
