package com.g.city.auth.service;

import com.g.city.auth.entity.UserMst;

import java.util.Optional;

public interface UserMstService {
    Optional<UserMst> findUserByUsername(String username);

    Long countByUsername(String username);

    boolean saveOne(UserMst userMst);
}
