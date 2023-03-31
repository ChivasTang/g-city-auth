package com.g.city.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.g.city.auth.entity.UserMst;
import com.g.city.auth.mapper.UserMstMapper;
import com.g.city.auth.service.UserMstService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserMstServiceImpl implements UserMstService {
    @Resource
    private UserMstMapper userMstMapper;

    @Override
    public Optional<UserMst> findUserByUsername(String username) {
        final QueryWrapper<UserMst> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return Optional.of(userMstMapper.selectOne(queryWrapper));
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
