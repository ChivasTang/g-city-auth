package com.g.city.auth.service;

import com.g.city.auth.entity.UserMst;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserMstService extends UserDetailsService {
    UserDetails loadUserByUserId(String userId);

    UserDetails getUserDetails(UserMst userMst);

    UserMst findUser(String eqStr, String conditionStr);

    Long countByUsername(String username);

    boolean saveOne(UserMst userMst);
}
