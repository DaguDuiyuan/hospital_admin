package org.simple.wx.service;

import cn.zhxu.data.Mapper;

public interface GuaHaoService {

    /**
     * 获取科室列表
     * @return
     */
    Mapper getKeShiList();

    /**
     * 获取科室医生列表
     * @return
     */
    Mapper getKeShiDoctorList();
}
