package cn.edu.ahut.Service.Impl;

import cn.edu.ahut.Entity.Record;
import cn.edu.ahut.Entity.User;
import cn.edu.ahut.Mappers.DepartmentMapper;
import cn.edu.ahut.Mappers.RecordMapper;
import cn.edu.ahut.Mappers.UserMapper;
import cn.edu.ahut.Service.RecordService;
import cn.edu.ahut.utils.Base64ToImg;
import cn.edu.ahut.utils.DeleteFile;
import cn.edu.ahut.utils.Result;
import com.arcsoft.fd.FaceDetection;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhushuangfei on 2018/3/13
 */

@Service
public class RecordServiceImpl implements RecordService {

    private FaceDetection faceDetection;

    @Autowired
    RecordMapper recordMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    public RecordServiceImpl(FaceDetection faceDetection) {
        this.faceDetection = faceDetection;
    }

    @Override
    public Result saveRecord(String image) {
        Result result = new Result();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String fileName = df.format(now) + ".png";
        String path = System.getProperty("user.dir") + File.separator + "images";
        //创建目录
        File tempFile = new File(path);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        //保存图片到本地
        Base64ToImg.generateImage(image.substring(22), path + File.separator + fileName);

        List<String> list = new ArrayList<>();
        list.add(path + File.separator + fileName);
        List<FaceDetection.FaceGeoInfo> result1 = faceDetection.recognizeFacesBatch(list, "_", 0.5F);
        JSONArray jsonArray = new JSONArray();
        for (FaceDetection.FaceGeoInfo s : result1) {
            if (!s.getId().contains("unknown")) {
                String departmentCode = (s.getId().split("_")[1]);
                String userCode = (s.getId().split("_")[2]);
                Map<String, Object> m = new HashMap<>();
                m.put("departmentCode", departmentCode);
                m.put("userCode", userCode);
                User user = userMapper.getUserByDCodeUCode(m);
                Record record = new Record();
                record.setImage(path + File.separator + fileName);
                record.setUserId(user.getId());
                record.setArriveTime(now);
                int num = recordMapper.insert(record);
                if (num == 1) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("userName", user.getUserName());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = sdf.format(now);
                    jsonObject.put("arriveTime", time);
                    jsonArray.add(jsonObject);
                }
            }
        }
        if (jsonArray.isEmpty()) {
            DeleteFile.delete(path + File.separator + fileName);
            result.setCode(1);
            result.setMessage("No User");
            return result;
        }
        DeleteFile.delete(path + File.separator + fileName);
        result.setCode(0);
        result.setMessage("No Error");
        result.setResult(jsonArray);
        return result;
    }

    @Override
    public Result listRecord(String userId, String userName, String departmentName,  String startTime, String endTime) {
        Result result = new Result();
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("userName", userName);
        map.put("departmentName", departmentName);
        if(startTime != null)
            startTime = startTime+" 00:00:00";
        map.put("startTime", startTime);
        if(endTime != null)
            endTime = endTime+" 23:59:59";
        map.put("endTime", endTime);
        List<Record> list = recordMapper.listRecord(map);
        JSONArray array = new JSONArray();
        for (Record record : list) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("id", record.getId());
            map1.put("userName", record.getUserName());
            map1.put("userCode",record.getUserCode());
            map1.put("departmentName",record.getDepartmentName());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            map1.put("arriveTime", df.format(record.getArriveTime()));
            array.add(map1);
        }
        result.setCode(0);
        result.setMessage("No Error");
        result.setResult(array);
        return result;
    }

    @Override
    public Result getRecordById(int id) {
        Result result = new Result();
        Record record = recordMapper.getRecordById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", record.getId());
        jsonObject.put("userName", record.getUserName());
        jsonObject.put("userCode",record.getUserCode());
        jsonObject.put("departmentName",record.getDepartmentName());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonObject.put("arriveTime", df.format(record.getArriveTime()));
        result.setCode(0);
        result.setMessage("No Error");
        result.setResult(jsonObject);
        return result;
    }

    @Override
    public Result updateRecord(int id, String arriveTime) {
        Result result = new Result();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("arriveTime", arriveTime);
        int num = recordMapper.updateRecord(map);
        if (num != 1) {
            result.setCode(1);
            result.setMessage("Error");
            return result;
        }
        result.setCode(0);
        result.setMessage("No Error");
        return result;
    }

    @Override
    public Result deleteRecord(int id) {
        Result result = new Result();
        int num = recordMapper.deleteByPrimaryKey(id);
        if (num != 1) {
            result.setCode(1);
            result.setMessage("Error");
            return result;
        }
        result.setCode(0);
        result.setMessage("No Error");
        return result;
    }
}
