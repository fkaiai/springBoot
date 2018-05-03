package cn.fk.te.mapper;

import cn.fk.te.entity.User;

import java.util.List;

public interface UserMapper {

    int insert(User user);

    List<User> selectAll();

}