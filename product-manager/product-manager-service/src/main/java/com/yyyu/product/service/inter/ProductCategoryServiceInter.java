package com.yyyu.product.service.inter;

import com.github.pagehelper.PageInfo;
import com.yyyu.product.pojo.MallProductCategory;

import java.util.List;

/**
 * 功能：商品分类相关接口
 *
 * @author yu
 * @date 2018/1/29.
 */
public interface ProductCategoryServiceInter {


    /**
     * 得到所有商品分类信息
     *
     * @return
     */
    List<MallProductCategory> selectAllProductCategory();

    /**
     * 分页得到商品分类信息
     *
     * @param start
     * @param size
     * @return
     */
    PageInfo<MallProductCategory> selectProductCategoryByPage(Integer start , Integer size);

    /**
     *根据分类id获得商品分类信息
     *
     * @param categoryId
     * @return
     */
    MallProductCategory selectProductCategoryByCategoryId(Long categoryId);

    /**
     * 修改商品信息
     *
     * @param mallProductCategory
     */
    void updateProductCategory(MallProductCategory mallProductCategory);

    /**
     * 删除商品分类
     *
     * 真正意义上的删除
     * @param categoryId
     */
    void reallyDeleteProductCategory(Long categoryId);

    /**
     * 删除商品分类
     *
     * 只是将status设置为2
     * @param categoryId
     */
    void deleteProductCategory(Long categoryId);

    /**
     * 添加商品分类
     *
     * @param mallProductCategory
     */
    void addProductCategory(MallProductCategory mallProductCategory);
}
