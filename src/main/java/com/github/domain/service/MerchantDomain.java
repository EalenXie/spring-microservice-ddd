package com.github.domain.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.application.service.MerchantService;
import com.github.domain.entity.Merchant;
import com.github.domain.entity.Store;
import com.github.domain.repository.MerchantRepository;
import com.github.domain.repository.MerchantStoreRelRepository;
import com.github.domain.repository.StoreRepository;
import com.github.domain.vo.MerchantStoreRel;
import com.github.domain.vo.Rate;
import com.github.infrastructure.base.error.BusinessException;
import com.github.infrastructure.base.resp.RespBody;
import com.github.interfaces.dto.AddStoreDTO;
import com.github.interfaces.dto.MerchantComeInDTO;
import com.github.interfaces.dto.StoreDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author EalenXie create on 2021/3/1 15:59
 * 商户领域对象 完成 application层 委托的功能  (与领域服务不是同一概念 本例中没有领域服务案例)
 */
@Service
public class MerchantDomain implements MerchantService {

    @Resource
    private MerchantRepository merchantRepository;
    @Resource
    private StoreRepository storeRepository;
    @Resource
    private MerchantStoreRelRepository merchantRelRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 获取商户信息(获取聚合根信息)
     *
     * @param merchantId 商户Id
     */
    @Override
    @Transactional(readOnly = true)
    public RespBody<Merchant> getMerchant(Long merchantId) {
        // 1. 获取 商户对象(聚合根实体)
        Merchant merchant = merchantRepository.getById(merchantId);
        if (merchant == null) return RespBody.ok(null);
        // 2. 获取商户门店信息(实体)
        merchant.setStores(storeRepository.getStoresByMerchantById(merchant.getMerchantId()));
        // 3. 从配置文件中读取 商户的固定费率信息(值对象)
        try {
            URL file = Objects.requireNonNull(getClass().getClassLoader().getResource("rate_vo.json"));
            merchant.setRates(objectMapper.readValue(file, new TypeReference<List<Rate>>() {
            }));
        } catch (IOException e) {
            // 读取或解析失败 ignore
        }
        return RespBody.ok(merchant);
    }

    /**
     * 检查商户名称是否合法
     *
     * @param merchantName 商户名称
     * @param exclude      需要判断排除掉的商户Id 为空则无需排除(例如新增时无需排除,更新时排除自身)
     */
    @Override
    public boolean checkMerchantName(String merchantName, Long... exclude) {
        // 这里判断名字不可重复即合法
        int count = merchantRepository.checkMerchantName(merchantName, exclude);
        return count > 0;
    }

    /**
     * 商户 入驻(即商户在平台注册)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespBody<Void> merchantComeIn(MerchantComeInDTO dto) {
        // 1. 检查商户用户名是否合法
        if (checkMerchantName(dto.getMerchantName())) {
            throw new BusinessException("1000", "商户名已经存在,请重新输入");
        }
        // 2. 属性拷贝,数据持久化
        merchantRepository.save(dto.copy(Merchant.class));
        return RespBody.ok();
    }

    /**
     * 商户 新增门店(商户可以新增一家或多家门店)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespBody<Void> addStore(AddStoreDTO dto) {
        // 1. 检查商户是否存在
        if (merchantRepository.getById(dto.getMerchantId()) == null) {
            throw new BusinessException("1001", "商户不存在");
        }
        // 2. 建立起门店信息与商户关联信息 保存门店信息(实体信息)
        List<MerchantStoreRel> relList = new ArrayList<>();
        for (StoreDTO store : dto.getStores()) {
            Store s = store.copy(Store.class);
            storeRepository.save(s);
            relList.add(new MerchantStoreRel(dto.getMerchantId(), s.getStoreId()));
        }
        merchantRelRepository.saveBatch(relList);
        return RespBody.ok();
    }
}
