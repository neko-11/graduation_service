package cn.edu.ahut.Service;

import cn.edu.ahut.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhushuangfei on 2018/3/19
 */
public interface UserService {

    Result listAllUser();

    Result getUserById(int id);

    Result saveUser(String userName, String userCode, String departmentName, String role);

    Result updateImage(int id, MultipartFile image);

    Result deleteUserById(int id);

    Result updateUser(int id, String userName, String userCode, String departmentName, String role);

    Result login(String image);
}
