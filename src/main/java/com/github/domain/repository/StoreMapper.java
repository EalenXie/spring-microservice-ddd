package com.github.domain.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.domain.entity.Store;

import java.util.List;

/**
 * @author EalenXie create on 2021/3/1 15:18
 */
public interface StoreMapper extends BaseMapper<Store> {


    /**
     * 获取商户下拥有的门店
     *
     * @param merchantId 根据商户Id进行获取
     * @return 门店数据信息
     */
    List<Store> getStoresByMerchantById(Long merchantId);
}
