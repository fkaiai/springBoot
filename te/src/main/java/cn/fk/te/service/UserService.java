package cn.fk.te.service;

import cn.fk.te.entity.User;

import java.util.List;

public interface UserService {

    int insert(User user);

    List<User> selectAll();
}
