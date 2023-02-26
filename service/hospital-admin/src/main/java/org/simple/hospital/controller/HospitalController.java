package org.simple.hospital.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import org.simple.hospital.entity.Hospital;
import org.simple.hospital.service.HospitalService;
import org.simple.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright: simple
 * @Date: 2023-02-26 14:04:11
 * @Author: dagu
 */

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;


    @GetMapping("list")
    @Operation(summary = "查询医院信息表")
    @SaCheckPermission(value = {"hospital:config:query"}, mode = SaMode.OR)
    public CommonResult<Page<Hospital>> list(Page<Hospital> page, Hospital hospital) {
        return CommonResult.success(hospitalService.page(page, Wrappers.query(hospital)));
    }


}