package org.simple.wx;

import cn.zhxu.data.Mapper;
import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.SHttpTask;
import cn.zhxu.okhttps.fastjson2.Fastjson2MsgConvertor;
import org.simple.wx.entity.HttpEntity;
import org.simple.wx.enums.HttpMethods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpUtil {

    @Value("${system.frp-config.base-url}")
    private String baseUrl;

    HTTP frpHttp = HTTP.builder()
            .addMsgConvertor(new Fastjson2MsgConvertor())
            .bodyType("json")
            .build();

    public Mapper request(HttpEntity entity) {
        String fullUrl = String.format("http://%s.%s%s", entity.getFrpName(), baseUrl, entity.getUrl());
        SHttpTask task = frpHttp
                .sync(fullUrl)
                .addBodyPara(entity.getData());

        if(entity.getMethod() == null) {
            entity.setMethod(HttpMethods.GET);
        }

        Mapper mapper;

        if (entity.getMethod() == HttpMethods.GET) {
            return task.get().getBody().toMapper();
        } else {
            return task.post().getBody().toMapper();
        }
    }
}
