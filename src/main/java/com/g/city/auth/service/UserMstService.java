package com.g.city.auth.service;

import com.g.city.auth.entity.UserMst;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserMstService extends UserDetailsService {
    UserMst findUserByUsername(String username);

    Long countByUsername(String username);

    boolean saveOne(UserMst userMst);
}
