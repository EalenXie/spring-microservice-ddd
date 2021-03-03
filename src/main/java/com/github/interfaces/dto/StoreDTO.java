package com.github.interfaces.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author EalenXie create on 2021/3/1 15:02
 */
@Data
public class StoreDTO implements Copier {
    /**
     * 门店名称
     */
    @NotEmpty(message = "门店名称不允许为空")
    private String storeName;
    /**
     * 门店地址
     */
    @NotEmpty(message = "门店地址不允许为空")
    private String address;
    /**
     * 营业时间 例如 09:00 - 23:00
     */
    private String businessHours;
    /**
     * 门店电话
     */
    @NotEmpty(message = "门店电话不允许为空")
    private String mobile;
}
