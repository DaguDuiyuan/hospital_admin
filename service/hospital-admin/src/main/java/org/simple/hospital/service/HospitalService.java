package org.simple.hospital.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.simple.hospital.entity.FrpClient;
import org.simple.hospital.entity.Hospital;

import java.util.List;


/**
 * @Copyright: simple
 * @Date: 2023-02-26 14:04:11
 * @Author: dagu
 */
public interface HospitalService extends IService<Hospital> {

    List<FrpClient> getFrpClients();

    Page<Hospital> queryPage(Page<Hospital> page, Hospital hospital);
}