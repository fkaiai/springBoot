package cn.fk.te.mapper;

import cn.fk.te.entity.User;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    int insert(User user);

}