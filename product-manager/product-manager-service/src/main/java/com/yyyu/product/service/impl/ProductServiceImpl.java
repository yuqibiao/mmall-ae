package com.yyyu.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyyu.product.dao.MallProductMapper;
import com.yyyu.product.pojo.MallProduct;
import com.yyyu.product.pojo.MallProductExample;
import com.yyyu.product.pojo.MallProductWithBLOBs;
import com.yyyu.product.service.inter.ProductServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/29.
 */
@Service
public class ProductServiceImpl implements ProductServiceInter{

    @Autowired
    private MallProductMapper mallProductMapper;

    @Override
    public Map<Long, MallProduct> selectProductsMap(List<Long> productIdList) {

        Map<Long, MallProduct> priceMap = new HashMap<>();
        MallProductExample example = new MallProductExample();
        example.createCriteria().andProductIdIn(productIdList);
        List<MallProduct> mallProducts = mallProductMapper.selectByExample(example);
        for (MallProduct product: mallProducts) {
            Long productId = product.getProductId();
            BigDecimal price = product.getPrice();
            priceMap.put(productId , product);
        }

        return priceMap;
    }

    @Override
    public PageInfo<MallProduct> selectProductPageByCategoryId(Long categoryId , Integer start, Integer size , MallProductExample mallProductExample) {
        PageHelper.offsetPage(start , size);
        mallProductExample.createCriteria().andCategoryIdEqualTo(categoryId);
        List<MallProduct> mallProducts = mallProductMapper.selectByExample(mallProductExample);
        return new PageInfo<>(mallProducts);
    }

    @Override
    public MallProductWithBLOBs selectMallProductByProductId(Long productId) {
        return mallProductMapper.selectByPrimaryKey(productId);
    }

    @Override
    public void updateProduct(MallProductWithBLOBs product) {
        mallProductMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public void deleteProductByProductId(Long productId) {
        mallProductMapper.deleteByPrimaryKey(productId);
    }

    @Override
    public void addProduct(MallProductWithBLOBs product) {
        mallProductMapper.insertSelective(product);
    }

    @Override
    public void deleteProductByProductIdList(List<Long> productIdList) {

        MallProductExample productExample = new MallProductExample();
        productExample.createCriteria().andProductIdIn(productIdList);

        mallProductMapper.deleteByExample(productExample);

    }
}
