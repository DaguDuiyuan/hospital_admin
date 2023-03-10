package org.simple.hospital.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * @Copyright: simple
 * @Desc: 医院信息表实体
 * @Date: 2023-02-26 14:04:11
 * @Author: dagu
 */

@Data
@NoArgsConstructor
@TableName(value = "busi_hospital")
public class Hospital implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    private String hospitalName;

    private String ngrokName;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    @TableField(exist = false)
    private String frpStatus = "unknown";

    ///----------------------- 收钱吧参数 --------------------------
    private String vendorSn; // 服务商序列号

    private String vendorKey; // 服务商密钥

    private String appId;

    private String code;

    ///----------------------- 收钱吧参数 --------------------------
}
