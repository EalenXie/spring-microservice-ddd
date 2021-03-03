package com.github.interfaces.facade;

import com.github.application.service.MerchantService;
import com.github.domain.entity.Merchant;
import com.github.infrastructure.base.resp.RespBody;
import com.github.interfaces.dto.AddStoreDTO;
import com.github.interfaces.dto.MerchantComeInDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author EalenXie create on 2018/8/30 18:10.
 * 为远程客户端提供粗粒度的调用接口
 */
@RestController
public class MerchantFacade {

    @Resource
    private MerchantService merchantService;

    /**
     * 获取商户信息
     */
    @GetMapping("/getMerchant")
    public RespBody<Merchant> getMerchant(@RequestParam Long merchantId) {
        return merchantService.getMerchant(merchantId);
    }

    /**
     * 商户 入驻(即商户在平台注册)
     */
    @PostMapping("/merchantComeIn")
    public RespBody<Void> merchantComeIn(@Valid @RequestBody MerchantComeInDTO dto) {
        return merchantService.merchantComeIn(dto);
    }

    /**
     * 商户 新增门店(商户可以新增一家或多家门店)
     */
    @PostMapping("/addStore")
    public RespBody<Void> addStore(@Valid @RequestBody AddStoreDTO dto) {
        return merchantService.addStore(dto);
    }
}
