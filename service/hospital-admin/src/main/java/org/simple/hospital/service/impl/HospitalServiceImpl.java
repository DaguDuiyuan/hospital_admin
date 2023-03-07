package org.simple.hospital.service.impl;

import cn.zhxu.data.Mapper;
import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.HttpTask;
import cn.zhxu.okhttps.Preprocessor;
import cn.zhxu.okhttps.fastjson2.Fastjson2MsgConvertor;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.simple.hospital.entity.FrpClient;
import org.simple.hospital.entity.Hospital;
import org.simple.hospital.mapper.HospitalMapper;
import org.simple.hospital.service.HospitalService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


/**
 * @Copyright: simple
 * @Date: 2023-02-26 14:04:11
 * @Author: dagu
 */


@Service
public class HospitalServiceImpl
        extends ServiceImpl<HospitalMapper, Hospital>
        implements HospitalService {

    HTTP frpHttp = HTTP.builder()
            .baseUrl("http://119.91.224.190:17500")
            .addPreprocessor((Preprocessor.PreChain chain) -> {
                HttpTask<?> task = chain.getTask();
                task.addHeader("Authorization", "Basic SGNiZEFZT09xSTomOCUyakNffmdaa1JpSXA=");
                chain.proceed();
            })
            .addMsgConvertor(new Fastjson2MsgConvertor())
            .build();


    @Override
    public List<FrpClient> getFrpClients() {
        return frpHttp.sync("/api/proxy/http")
                .get()
                .getBody()
                .toMapper()
                .getArray("proxies")
                .toList(FrpClient.class);
    }

    @Override
    public Page<Hospital> queryPage(Page<Hospital> page, Hospital hospital) {
        // 获取Frp的所有连接信息
        List<FrpClient> frpClients = this.getFrpClients();
        // 分页查询
        Page<Hospital> pageList = this.page(page, Wrappers.query(hospital));

        pageList.getRecords().forEach((e) -> frpClients.stream()
                .filter((f) -> f.getName().equals(e.getNgrokName()))
                .findFirst()
                .ifPresent((f) -> e.setFrpStatus(f.getStatus())));

        return pageList;
    }
}