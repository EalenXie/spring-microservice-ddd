package com.github.application.service;

import com.github.domain.entity.Merchant;
import com.github.infrastructure.base.resp.RespBody;
import com.github.interfaces.dto.AddStoreDTO;
import com.github.interfaces.dto.MerchantComeInDTO;

/**
 * @author EalenXie create on 2018/8/30 18:10.
 * 商户领域 Application 层 , 此层规定本服务中 商户领域 需要完成的任务和功能
 * 不包含任务业务规则或知识, 为领域对象 委托工作。
 * 以下为示例部分内容
 */
public interface MerchantService {
    /**
     * 获取商户信息(获取聚合根)
     *
     * @param merchantId 商户Id
     */
    RespBody<Merchant> getMerchant(Long merchantId);

    /**
     * 检查商户名称是否合法
     *
     * @param merchantName 商户名称
     * @param exclude      需要判断排除掉的商户Id 为空则无需排除(例如新增时无需排除,更新时排除自身)
     * @return 是否合法
     */
    boolean checkMerchantName(String merchantName, Long... exclude);

    /**
     * 商户 入驻(即商户在平台注册)
     */
    RespBody<Void> merchantComeIn(MerchantComeInDTO dto);

    /**
     * 商户 新增门店(商户可以新增一家或多家门店)
     */
    RespBody<Void> addStore(AddStoreDTO dto);


}
