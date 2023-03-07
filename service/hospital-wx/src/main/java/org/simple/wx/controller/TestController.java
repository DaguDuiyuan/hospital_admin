package org.simple.wx.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.zhxu.data.Mapper;
import org.simple.wx.service.GuaHaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@SaIgnore
public class TestController {

    @Resource
    GuaHaoService guaHaoService;

    @GetMapping("/test")
    public Mapper test() {
        return guaHaoService.getKeShiList();
    }
}
