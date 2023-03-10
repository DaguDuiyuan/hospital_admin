package org.simple.wx.service;

import cn.zhxu.data.Mapper;
import org.simple.wx.entity.HttpEntity;

public interface HttpService {

    /**
     * 获取数据
     * @return
     */
    Mapper getHttpResult(HttpEntity entity);

}
