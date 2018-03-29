package cn.edu.ahut.Mappers;

import cn.edu.ahut.Entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {

    List<User> listUser();

    User getUserById(int id);

    int getCountByCode(Map map);

    int getIdByCode(Map map);

    User getUserByDCodeUCode(Map map);

    int updateImgById(Map map);

    int updateUser(Map map);
}