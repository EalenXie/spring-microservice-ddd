package com.github.domain.vo;


import lombok.Data;

/**
 * @author EalenXie create on 2021/3/1 9:28
 * 商户费率 值对象 持久化对象
 * 无唯一标识 , 具有不可变性 只是一些基本值属性 , 由商户进行关联 产生实际意义
 * 因其在此场景中具有不变性,且无事务关联性(类似于字典值之类) 笔者这里简单的将其持久化在一个单独的json文件(本工程下的rate_vo.json)
 */
@Data
public class Rate {
    /**
     * 渠道名称 比如 微信 支付宝 银联
     * 例如 : AliPay(支付宝)
     */
    private String channel;
    /**
     * 费率值
     * 例如 : 千分之一(0.001)
     */
    private Double value;
    /**
     * 备注说明
     * 例如 : 平台商户对支付宝的费率说明
     */
    private String remarks;


}
