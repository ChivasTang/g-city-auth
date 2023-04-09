package com.g.city.auth.service.impl;

import com.g.city.auth.entity.AccHist;
import com.g.city.auth.mapper.AccHistMapper;
import com.g.city.auth.service.AccHistService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AccHistServiceImpl implements AccHistService {

    @Resource
    private AccHistMapper accHistMapper;

    @Override
    public void save(HttpServletRequest request, String userId) {
        final AccHist accHist = new AccHist();
        final String acc_id = UUID.randomUUID().toString();
        final String url = request.getRequestURI();
        accHist.setAccId(acc_id);
        accHist.setUrl(url);
        if (!StringUtils.isEmpty(userId)) {
            accHist.setUserId(userId);
        }
        accHistMapper.insert(accHist);
        log.debug("AccHistService - saveOne: {} by user: {}", accHist.getUrl(), accHist.getUserId());
    }
}
