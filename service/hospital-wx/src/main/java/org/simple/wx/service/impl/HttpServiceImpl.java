package org.simple.wx.service.impl;

import cn.zhxu.data.Mapper;
import org.simple.wx.HttpUtil;
import org.simple.wx.entity.HttpEntity;
import org.simple.wx.service.HttpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HttpServiceImpl implements HttpService {

    @Resource
    HttpUtil httpUtil;

    @Override
    public Mapper getHttpResult(HttpEntity entity) {
        return httpUtil.request(entity);
    }

}
