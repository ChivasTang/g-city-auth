package com.g.city.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.g.city.auth.entity.UserMst;
import com.g.city.auth.mapper.UserMstMapper;
import com.g.city.auth.service.UserMstService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserMstServiceImpl implements UserMstService {
    @Resource
    private UserMstMapper userMstMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserMst userMst = findUserByUsername(username);
        if (userMst == null) {
            return null;
        }
        return User.builder()
                .username(userMst.getUsername())
                .password(userMst.getPassword())
                .roles(userMst.getRoles())
                .authorities(userMst.getAuthorities())
                .build();
    }


    @Override
    public UserMst findUserByUsername(String username) {
        final UserMst userMst = new UserMst();
        final QueryWrapper<UserMst> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_id", "username", "password");
        queryWrapper.eq("username", username);
        final UserMst found = userMstMapper.selectOne(queryWrapper);
        if (found == null) {
            return null;
        }
        userMst.setUserId(found.getUserId());
        userMst.setUsername(found.getUsername());
        userMst.setPassword(found.getPassword());
        userMst.setRoles(found.getRoles());
        userMst.setAuthorities(found.getAuthorities());
        return userMst;
    }

    @Override
    public Long countByUsername(String username) {
        final QueryWrapper<UserMst> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        final Long count = userMstMapper.selectCount(queryWrapper);
        return count != null ? count : 0L;
    }

    @Override
    public boolean saveOne(UserMst userMst) {
        final int insert = userMstMapper.insert(userMst);
        return insert != 0;
    }
}
