package org.simple.wx.entity;

import lombok.Builder;
import lombok.Data;
import org.simple.wx.enums.HttpMethods;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
public class HttpEntity implements Serializable {


    private String frpName;

    private HttpMethods method;

    private String url;

    private Map<String, Object> data;
}
