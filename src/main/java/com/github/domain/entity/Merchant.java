package com.github.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.domain.vo.Rate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author EalenXie create on 2021/3/1 9:14
 * 商户实体  既是商户实体 持久化对象也是商户领域的根实体 (贫血模型)
 * 属性示例
 */
@Data
@TableName("merchant")
public class Merchant {
    /**
     * 商户Id
     */
    @TableId(value = "merchant_id", type = IdType.AUTO)
    private Long merchantId;
    /**
     * 商户名称
     */
    @TableField(value = "merchant_name")
    private String merchantName;
    /**
     * 商户类型
     */
    @TableField(value = "merchant_type")
    private String merchantType;
    /**
     * 法定代表人
     */
    @TableField(value = "legal_person")
    private String legalPerson;
    /**
     * 商户入驻时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "come_in_time")
    private Date comeInTime;
    /**
     * 一个商户拥有一家或多家 门店(实体)
     */
    @TableField(exist = false)
    private List<Store> stores;
    /**
     * 一个商户拥有多个对外渠道 费率(值对象)
     */
    @TableField(exist = false)
    private List<Rate> rates;


}
