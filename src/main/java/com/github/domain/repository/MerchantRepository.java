package com.github.domain.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.domain.entity.Merchant;
import org.springframework.stereotype.Service;

/**
 * @author EalenXie create on 2021/3/2 11:56
 */
@Service
public class MerchantRepository extends ServiceImpl<MerchantMapper, Merchant> {

    public int checkMerchantName(String merchantName, Long[] exclude) {
        return baseMapper.checkMerchantName(merchantName, exclude);
    }
}
