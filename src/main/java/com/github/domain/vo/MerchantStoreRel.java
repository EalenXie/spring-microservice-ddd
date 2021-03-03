package com.github.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author EalenXie create on 2021/3/2 13:51
 * 商户门店关联 值对象 持久化对象
 * 本质上标识一个中间关系 无唯一标识
 * 因其具有事务上的关联性 , 将其持久化在数据库中
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("merchant_store_rel")
public class MerchantStoreRel {
    @TableField("merchant_id")
    private Long merchantId;
    @TableField("store_id")
    private Long storeId;

}
