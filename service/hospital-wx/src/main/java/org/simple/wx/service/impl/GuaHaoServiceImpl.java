package org.simple.wx.service.impl;

import cn.zhxu.data.Mapper;
import org.simple.wx.HttpUtil;
import org.simple.wx.entity.HttpEntity;
import org.simple.wx.service.GuaHaoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GuaHaoServiceImpl implements GuaHaoService {

    @Resource
    HttpUtil httpUtil;

    @Override
    public Mapper getKeShiList() {
        return httpUtil.request(HttpEntity.builder()
                .url("/test")
                .frpName("test")
                .build());
    }

    @Override
    public Mapper getKeShiDoctorList() {
        return null;
    }
}
