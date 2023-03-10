package org.simple.wx.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.simple.wx.entity.HttpEntity;
import org.simple.wx.enums.HttpMethods;
import org.simple.wx.service.HttpService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/wx-api")
@SaIgnore
public class HttpController {

    @Resource
    HttpService httpService;

    @GetMapping("/{frpName}/{path}")
    public Map<String, Object> httpGet(
            @PathVariable String frpName,
            @PathVariable String path
    ) {
        return httpService.getHttpResult(HttpEntity.builder()
                .url("/" + path)
                .frpName(frpName)
                .method(HttpMethods.GET)
                .build()).toMap();
    }

    @PostMapping("/{frpName}/{path}")
    public Map<String, Object> httpPost(
            @PathVariable String frpName,
            @PathVariable String path,
            @RequestBody Map<String, Object> data
    ) {
        return httpService.getHttpResult(HttpEntity.builder()
                .url("/" + path)
                .frpName(frpName)
                .method(HttpMethods.POST)
                .data(data)
                .build()).toMap();
    }

}
