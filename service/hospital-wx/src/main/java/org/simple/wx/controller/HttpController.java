package org.simple.wx.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.simple.wx.entity.HttpEntity;
import org.simple.wx.enums.HttpMethods;
import org.simple.wx.service.HttpService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
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
            @PathVariable String path,
            @RequestParam Map<String, Object> data
    ) {
        return httpService.getHttpResult(HttpEntity.builder()
                .url("/" + path)
                .frpName(frpName)
                .data(data)
                .method(HttpMethods.GET)
                .build()).toMap();
    }

    @PostMapping("/{frpName}/{path}")
    public Map<String, Object> httpPost(
            @PathVariable String frpName,
            @PathVariable String path,
            HttpServletRequest request
    ) {
        return httpService.getHttpResult(HttpEntity.builder()
                .url("/" + path)
                .frpName(frpName)
                .method(HttpMethods.POST)
                .data(parseMap(request))
                .build()).toMap();
    }

    public Map<String, Object> parseMap(HttpServletRequest request){
        try {
            return JSONObject.parseObject(getRequestPostStr(request));
        }catch (Exception e){
            return new HashMap<>();
        }
    }

    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte[] buffer = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        if (buffer == null) return null;
        return new String(buffer, charEncoding);
    }


    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte[] buffer = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

}
