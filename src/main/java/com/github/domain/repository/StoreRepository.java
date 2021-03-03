package com.github.domain.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.domain.entity.Store;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author EalenXie create on 2021/3/2 11:56
 */
@Service
public class StoreRepository extends ServiceImpl<StoreMapper, Store> {

    public List<Store> getStoresByMerchantById(Long merchantId) {
        return baseMapper.getStoresByMerchantById(merchantId);
    }
}
