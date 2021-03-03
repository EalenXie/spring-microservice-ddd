package com.github.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author EalenXie create on 2021/2/25 11:36
 * 门店实体 实体 持久化对象 (贫血模型) 具有唯一标识
 * 属性示例
 */
@Data
@TableName("store")
public class Store {
    /**
     * 门店Id 门店实体的唯一标识
     */
    @TableId(value = "store_id", type = IdType.AUTO)
    private Long storeId;
    /**
     * 门店名称
     */
    @TableField(value = "store_name")
    private String storeName;
    /**
     * 门店地址
     */
    @TableField(value = "address")
    private String address;
    /**
     * 营业时间 例如 09:00 - 23:00
     */
    @TableField(value = "business_hours")
    private String businessHours;
    /**
     * 门店电话
     */
    @TableField(value = "mobile")
    private String mobile;

}
