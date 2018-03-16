package com.yyyu.product.service.inter;

import com.github.pagehelper.PageInfo;
import com.yyyu.product.pojo.MallProduct;
import com.yyyu.product.pojo.MallProductExample;
import com.yyyu.product.pojo.MallProductWithBLOBs;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/29.
 */
public interface ProductServiceInter {

    Map<Long, MallProduct> selectProductsMap(List<Long> productIdList);

    /**
     * 分页查询商品信息
     *
     * @param  categoryId
     * @param start
     * @param size
     * @return
     */
    PageInfo<MallProduct> selectProductPageByCategoryId(Long categoryId , Integer start , Integer size , MallProductExample mallProductExample);

    /**
     * 根据商品id查询商品的信息
     *
     * @param productId
     * @return
     */
    MallProductWithBLOBs selectMallProductByProductId(Long productId);

    /**
     * 修改商品信息
     *
     * @param product
     */
    void updateProduct(MallProductWithBLOBs product);

    /**
     * 根据商品Id删除商品信息
     *
     * @param productId
     */
    void deleteProductByProductId(Long productId);

    /**
     * 添加商品信息
     *
     * @param product
     */
    void addProduct(MallProductWithBLOBs product);

    /**
     * 批量删除商品
     *
     * @param productIdList
     */
    void deleteProductByProductIdList(List<Long> productIdList);
}
