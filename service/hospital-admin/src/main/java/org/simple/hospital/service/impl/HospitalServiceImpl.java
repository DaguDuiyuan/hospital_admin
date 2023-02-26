package org.simple.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.simple.hospital.entity.Hospital;
import org.simple.hospital.mapper.HospitalMapper;
import org.simple.hospital.service.HospitalService;
import org.springframework.stereotype.Service;


/**
 * @Copyright: simple
 * @Date: 2023-02-26 14:04:11
 * @Author: dagu
 */


@Service
public class HospitalServiceImpl
        extends ServiceImpl<HospitalMapper, Hospital>
        implements HospitalService {

}