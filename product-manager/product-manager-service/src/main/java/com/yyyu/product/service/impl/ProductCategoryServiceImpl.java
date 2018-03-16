package com.yyyu.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyyu.product.dao.MallProductCategoryMapper;
import com.yyyu.product.pojo.MallProductCategory;
import com.yyyu.product.pojo.MallProductCategoryExample;
import com.yyyu.product.service.inter.ProductCategoryServiceInter;
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
public class ProductCategoryServiceImpl implements ProductCategoryServiceInter{

    @Autowired
    private MallProductCategoryMapper productCategoryMapper;

    @Override
    public List<MallProductCategory> selectAllProductCategory() {

        MallProductCategoryExample example = new MallProductCategoryExample();
        example.setDistinct(false);
        List<MallProductCategory> mallProductCategories = productCategoryMapper.selectByExample(example);

        return mallProductCategories;
    }

    @Override
    public PageInfo<MallProductCategory> selectProductCategoryByPage(Integer start, Integer size , MallProductCategoryExample productCategoryExample) {

        PageHelper.offsetPage(start , size);
        productCategoryExample.setDistinct(false);
        List<MallProductCategory> mallProductCategories = productCategoryMapper.selectByExample(productCategoryExample);

        return new PageInfo<>(mallProductCategories);
    }

    @Override
    public MallProductCategory selectProductCategoryByCategoryId(Long categoryId) {
        return productCategoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public void updateProductCategory(MallProductCategory mallProductCategory) {
        productCategoryMapper.updateByPrimaryKeySelective(mallProductCategory);
    }

    @Override
    public void reallyDeleteProductCategory(Long categoryId) {
        productCategoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public void deleteProductCategory(Long categoryId) {
        MallProductCategory category = new MallProductCategory();
        category.setCategoryId(categoryId);
        short status = 2;
        category.setStatus(status);
        productCategoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void addProductCategory(MallProductCategory mallProductCategory) {
        productCategoryMapper.insertSelective(mallProductCategory);
    }

    @Override
    public void deleteProductCategoryIdList(List<Long> categoryIdList) {

        MallProductCategoryExample categoryExample = new MallProductCategoryExample();
        categoryExample.createCriteria().andCategoryIdIn(categoryIdList);
        productCategoryMapper.deleteByExample(categoryExample);

    }
}
