package com.pactera.redisDome.service.impl;

import com.pactera.redisDome.gen.User;
import com.pactera.redisDome.mapper.UserMapper;
import com.pactera.redisDome.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zwk
 * @since 2024年01月07日
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
