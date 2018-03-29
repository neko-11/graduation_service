package cn.edu.ahut.Service;

import cn.edu.ahut.utils.Result;

import java.util.Date;

/**
 * Created by zhushuangfei on 2018/3/19
 */
public interface ScheduleService {

    Result listAll();

    Result getScheduleById(int id);

    Result saveSchedule(Date arriveTime, Date leaveTime);

    Result updateSchedule(int id, Date arriveTime, Date leaveTime);

    Result deleteSchedule(int id);
}
