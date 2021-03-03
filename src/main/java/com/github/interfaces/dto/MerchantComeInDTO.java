package com.github.interfaces.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author EalenXie create on 2021/3/1 10:22
 * 商户入驻 请求DTO
 */
@Data
public class MerchantComeInDTO implements Copier {
    /**
     * 商户名称
     */
    @NotEmpty(message = "商户名称,不允许为空")
    @Size(max = 200, message = "商户名称,字段长度不得超过200")
    private String merchantName;
    /**
     * 商户类型
     */
    @NotEmpty(message = "商户类型,不允许为空")
    @Size(max = 50, message = "商户类型不得超过50个字符")
    private String merchantType;
    /**
     * 法定代表人
     */
    @NotEmpty(message = "法定代表人,不允许为空")
    @Size(max = 10, message = "法定代表人长度不得超过10")
    private String legalPerson;

}
