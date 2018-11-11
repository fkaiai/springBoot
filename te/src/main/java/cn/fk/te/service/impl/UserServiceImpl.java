package cn.fk.te.service.impl;

import cn.fk.te.entity.User;
import cn.fk.te.mapper.UserMapper;
import cn.fk.te.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gyl
 */
@Service
public class UserServiceImpl extends AbstractBaseCRUDService<Long, User, UserMapper> implements UserService {
}
