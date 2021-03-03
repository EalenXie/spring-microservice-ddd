package com.github.interfaces.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author EalenXie create on 2021/3/1 11:28
 * 新增门店 DTO
 */
@Data
public class AddStoreDTO {

    /**
     * 商户Id  聚合根Id
     */
    @NotNull(message = "商户Id不允许为空")
    private Long merchantId;

    /**
     * 一个或多个门店信息
     */
    @Valid
    @NotNull(message = "新增的门店信息不允许为空")
    private List<StoreDTO> stores;


}
