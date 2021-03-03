package com.github.domain.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.domain.entity.Merchant;
import org.apache.ibatis.annotations.Param;

/**
 * @author EalenXie create on 2021/3/1 14:15
 * 仓储 提供底层查找和持久化对象的方法
 * 也就是 我们常说的mybatis的Mapper , jpa里面的Repository
 */
public interface MerchantMapper extends BaseMapper<Merchant> {


    Integer checkMerchantName(@Param("merchantName") String merchantName, @Param("exclude") Long[] exclude);


}
