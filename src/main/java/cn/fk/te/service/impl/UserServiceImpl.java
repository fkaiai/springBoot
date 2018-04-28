package cn.fk.te.service.impl;

import cn.fk.te.entity.User;
import cn.fk.te.mapper.UserMapper;
import cn.fk.te.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
//    @Transactional
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
