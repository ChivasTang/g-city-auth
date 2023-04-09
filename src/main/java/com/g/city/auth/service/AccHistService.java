package com.g.city.auth.service;

import jakarta.servlet.http.HttpServletRequest;

public interface AccHistService {
    void save(HttpServletRequest request, String userId);
}
