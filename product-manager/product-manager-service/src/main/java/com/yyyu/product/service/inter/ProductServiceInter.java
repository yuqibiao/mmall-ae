package com.yyyu.product.service.inter;

import com.github.pagehelper.PageInfo;
import com.yyyu.product.pojo.MallProduct;
import com.yyyu.product.pojo.MallProductWithBLOBs;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/1/29.
 */
public interface ProductServiceInter {

    /**
     * 分页查询商品信息
     *
     * @param start
     * @param size
     * @return
     */
    PageInfo<MallProductWithBLOBs> selectMallProductByPage(Integer start , Integer size);

    /**
     * 根据商品id查询商品的信息
     *
     * @param productId
     * @return
     */
    MallProduct selectMallProductByProductId(Long productId);

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

}
