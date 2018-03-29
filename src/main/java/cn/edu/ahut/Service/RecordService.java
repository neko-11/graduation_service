package cn.edu.ahut.Service;

import cn.edu.ahut.utils.Result;

/**
 * Created by zhushuangfei on 2018/3/13
 */
public interface RecordService {

    Result saveRecord(String image);

    Result listRecord(String userId, String userName, String departmentName, String startTime, String endTime);

    Result getRecordById(int id);

    Result updateRecord(int id,String arriveTime);

    Result deleteRecord(int id);
}
