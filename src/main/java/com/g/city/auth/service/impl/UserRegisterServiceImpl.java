package com.g.city.auth.service.impl;

import com.g.city.auth.dto.UserRegister;
import com.g.city.auth.entity.UserMst;
import com.g.city.auth.rest.req.ResultCode;
import com.g.city.auth.service.UserMstService;
import com.g.city.auth.service.UserRegisterService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserRegisterServiceImpl implements UserRegisterService {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMstService userMstService;

    @Override
    public ResultCode register(UserRegister userRegister) {
        //Check username password
        if (userRegister == null || StringUtils.isEmpty(userRegister.getUsername())) {
            return ResultCode.REGISTER_FAILED_USERNAME_NOT_INPUT;
        }
        final String rawPassword = userRegister.getPassword();
        if (StringUtils.isEmpty(rawPassword)) {
            return ResultCode.REGISTER_FAILED_PASSWORD_NOT_INPUT;
        }
        final String confirm = userRegister.getConfirm();
        if (!rawPassword.equals(confirm)) {
            return ResultCode.REGISTER_FAILED_PASSWORD_NOT_CONFIRMED;
        }
        final String username = userRegister.getUsername();
        Long userMstCount = userMstService.countByUsername(username);
        if (userMstCount > 0L) {
            return ResultCode.REGISTER_FAILED_USERNAME_EXISTED;
        }
        final String encodedPassword = passwordEncoder.encode(rawPassword);
        final UserMst userMst = new UserMst();
        userMst.setUsername(username);
        userMst.setPassword(encodedPassword);
        final String uuid = UUID.randomUUID().toString();
        userMst.setUserId(uuid);
        userMst.setCUserId(uuid);
        userMst.setUUserId(uuid);
        final boolean success = userMstService.saveOne(userMst);
        if (!success) {
            return ResultCode.REGISTER_FAILED_NOT_SAVED;
        }
        return ResultCode.SUCCESS_RESULT;
    }
}
