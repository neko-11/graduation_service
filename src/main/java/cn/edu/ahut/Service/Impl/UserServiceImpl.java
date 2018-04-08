package cn.edu.ahut.Service.Impl;

import cn.edu.ahut.Entity.Department;
import cn.edu.ahut.Entity.User;
import cn.edu.ahut.Mappers.DepartmentMapper;
import cn.edu.ahut.Mappers.UserMapper;
import cn.edu.ahut.Service.UserService;
import cn.edu.ahut.utils.Base64ToImg;
import cn.edu.ahut.utils.DeleteFile;
import cn.edu.ahut.utils.Result;
import cn.edu.ahut.utils.SaveImage;
import com.arcsoft.fd.FaceDetection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.google.common.io.Files.getFileExtension;

/**
 * Created by zhushuangfei on 2018/3/19
 */
@Service
public class UserServiceImpl implements UserService {

    private FaceDetection faceDetection;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    public UserServiceImpl(FaceDetection faceDetection) {
        this.faceDetection = faceDetection;
    }

    @Override
    public Result listAllUser() {
        Result result = new Result();
        List<User> list = userMapper.listUser();
        for (User user : list) {
            String path = user.getUserImg();
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                user.setUserImg(null);
            } else {
                user.setUserImg("data:image/png;base64," + Base64ToImg.getImageStr(path));
            }
        }
        result.setCode(0);
        result.setMessage("No Error");
        result.setResult(JSONArray.fromObject(list));
        return result;
    }

    @Override
    public Result getUserById(int id) {
        Result result = new Result();
        User user = userMapper.getUserById(id);
        String path = user.getUserImg();
        user.setUserImg("data:image/png;base64," + Base64ToImg.getImageStr(path));
        result.setCode(0);
        result.setMessage("No Error");
        result.setResult(JSONObject.fromObject(user));
        return result;
    }

    @Override
    public Result saveUser(String userName, String userCode, String departmentName, String role) {
        Result result = new Result();
        //判断code是否重复
        Map<String, Object> map = new HashMap<>();
        map.put("userCode", userCode);
        map.put("departmentName", departmentName);
        int count = userMapper.getCountByCode(map);
        if (count != 0) {
            result.setCode(1);
            result.setMessage("Repeat userCode");
            return result;
        }
        int departmentId = departmentMapper.getIdByName(departmentName);
        User user = new User();
        user.setUserName(userName);
        user.setUserCode(userCode);
        user.setDepartmentId(departmentId);
        user.setRole(role);
        int num = userMapper.insert(user);
        if (num == 1) {
            int id = userMapper.getIdByCode(map);
            result.setCode(0);
            result.setMessage("No Error");
            result.setResult(id);
            return result;
        }
        result.setCode(1);
        result.setMessage("Error");
        return result;
    }

    @Override
    public Result updateImage(int id, MultipartFile image) {
        Result result = new Result();
        try {
            User user = userMapper.selectByPrimaryKey(id);
            Department department = departmentMapper.selectByPrimaryKey(user.getDepartmentId());
            String fileName = "_" + department.getDepartmentCode() + "_" + user.getUserCode() + "." + getFileExtension(image.getOriginalFilename());
            String path = System.getProperty("user.dir") + File.separator + "photos" + File.separator + department.getDepartmentCode();
            //创建目录
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            File features = new File(System.getProperty("user.dir") + File.separator + "features");
            if (!features.exists()) {
                features.mkdirs();
            }
            //设置特征保存路径
            faceDetection.setFeatureFolder(System.getProperty("user.dir") + File.separator + "features" + File.separator);
            //保存图片到本地
            SaveImage.saveSingleFile(path, image, fileName);
            //调用算法
            File file = new File(path + File.separator + fileName);
            if (file.exists()) {
                String featurePath = faceDetection.registerFace(path + File.separator + fileName, "_" + department.getDepartmentCode() + "_" + user.getUserCode());
                //System.out.println(featurePath);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("userImg", path + File.separator + fileName);
            int num = userMapper.updateImgById(map);
            if (num == 1) {
                result.setCode(0);
                result.setMessage("No Error");
                return result;
            }
        } catch (Exception e) {
            result.setCode(1);
            result.setMessage("Exception");
            return result;
        }
        result.setCode(1);
        result.setMessage("Error");
        return result;
    }

    @Override
    public Result deleteUserById(int id) {
        Result result = new Result();
        //删除特征网中对应数据
        User user = userMapper.selectByPrimaryKey(id);
        Department department = departmentMapper.selectByPrimaryKey(user.getDepartmentId());
        faceDetection.removeFace("_" + department.getDepartmentCode() + "_" + user.getUserCode());
        DeleteFile.delete(user.getUserImg());
        int num = userMapper.deleteByPrimaryKey(id);
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
    public Result updateUser(int id, String userName, String userCode, String departmentName, String role) {
        Result result = new Result();
        int departmentId = departmentMapper.getIdByName(departmentName);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", id);
        map1.put("userName", userName);
        map1.put("userCode", userCode);
        map1.put("departmentId", departmentId);
        map1.put("role", role);
        int num1 = userMapper.updateUser(map1);
        if (num1 == 1) {
            result.setCode(0);
            result.setMessage("No Error");
            return result;
        }
        result.setCode(1);
        result.setMessage("Error");
        return result;
    }

    @Override
    public Result login(String image) {
        Result result = new Result();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String fileName = df.format(now) + ".png";
        String path = System.getProperty("user.dir") + File.separator + "login";
        try {
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
            if (result1.size() > 1) {
                result.setCode(1);
                result.setMessage("Too Many People");
                return result;
            }
            if (!result1.get(0).getId().contains("unknown")) {
                String departmentCode = (result1.get(0).getId().split("_")[1]);
                String userCode = (result1.get(0).getId().split("_")[2]);
                Map<String, Object> m = new HashMap<>();
                m.put("departmentCode", departmentCode);
                m.put("userCode", userCode);
                User user = userMapper.getUserByDCodeUCode(m);
                if (user != null) {
                    DeleteFile.delete(path + File.separator + fileName);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("userName", user.getUserName());
                    jsonObject.put("role", user.getRole());
                    jsonObject.put("id", user.getId());
                    result.setCode(0);
                    result.setMessage("No Error");
                    result.setResult(jsonObject);
                    return result;
                }
                DeleteFile.delete(path + File.separator + fileName);
                result.setCode(1);
                result.setMessage("Unknown People");
                return result;
            } else {
                DeleteFile.delete(path + File.separator + fileName);
                result.setCode(1);
                result.setMessage("Unknown People");
                return result;
            }
        } catch (Exception e) {
            DeleteFile.delete(path + File.separator + fileName);
            result.setCode(1);
            result.setMessage("Unknown People");
            return result;
        }
    }
}
