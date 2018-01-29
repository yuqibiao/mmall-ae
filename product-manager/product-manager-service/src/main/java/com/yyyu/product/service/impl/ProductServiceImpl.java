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

import java.util.List;

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
    public PageInfo<MallProductWithBLOBs> selectMallProductByPage(Integer start, Integer size) {
        PageHelper.offsetPage(start , size);
        List<MallProductWithBLOBs> mallProductWithBLOBs = mallProductMapper.selectByExampleWithBLOBs(new MallProductExample());
        return new PageInfo<>(mallProductWithBLOBs);
    }

    @Override
    public MallProduct selectMallProductByProductId(Long productId) {
        return mallProductMapper.selectByPrimaryKey(productId);
    }

    @Override
    public void updateProduct(MallProductWithBLOBs product) {
        mallProductMapper.updateByExampleSelective(product , new MallProductExample());
    }

    @Override
    public void deleteProductByProductId(Long productId) {
        mallProductMapper.deleteByPrimaryKey(productId);
    }

    @Override
    public void addProduct(MallProductWithBLOBs product) {
        mallProductMapper.insertSelective(product);
    }
}
