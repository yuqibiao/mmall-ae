package com.yyyu.mmall.controller.product;

import com.github.pagehelper.PageInfo;
import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.product.pojo.MallProduct;
import com.yyyu.product.pojo.MallProductWithBLOBs;
import com.yyyu.product.pojo.vo.ProductUpdateVo;
import com.yyyu.product.pojo.vo.ProductVo;
import com.yyyu.product.service.inter.ProductServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：商品api
 *
 * @author yu
 * @date 2018/1/30.
 */
@Api(value = "product", description = "商品信息相关",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/product")
@Controller
public class ProductController extends BaseController{

    @Autowired
    private ProductServiceInter productService;

    @ApiOperation(value = "分页查询某一分类下的商品商品", httpMethod = "GET")
    @RequestMapping(value = "v1/products/categories/{categoryId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getProductPageByCategoryId(@ApiParam(value = "商品分类Id", required = true) @PathVariable Long categoryId,
                                                  @ApiParam(value = "从第几行开始区数据") @RequestParam(defaultValue = "0") Integer start,
                                                  @ApiParam(value = "取数据的条数") @RequestParam(defaultValue = "10") Integer size) {

        PageInfo<MallProduct> mallProductPageInfo;
        try {
            mallProductPageInfo = productService.selectProductPageByCategoryId(categoryId, start, size);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallProductPageInfo);
    }

    @ApiOperation(value = "根据Id查询商品", httpMethod = "GET")
    @RequestMapping(value = "v1/products/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getProductByProductId(@ApiParam(value = "商品Id", required = true) @PathVariable Long productId) {

        MallProduct product;
        try {
            product = productService.selectMallProductByProductId(productId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(product);
    }

    @ApiOperation(value = "修改商品信息", httpMethod = "PATCH")
    @RequestMapping(value = "v1/products", method = RequestMethod.PATCH)
    @ResponseBody
    public ResultUtils updateProduct(@RequestBody ProductUpdateVo productUpdateVo) {

        try {
            productService.updateProduct(setProduct(productUpdateVo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("修改成功");
    }

    @ApiOperation(value = "根据Id删除商品", httpMethod = "DELETE")
    @RequestMapping(value = "v1/products/{productId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultUtils deleteProductByProductId(@ApiParam(value = "商品Id") @PathVariable Long productId) {

        try {
            productService.deleteProductByProductId(productId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除成功");
    }


    @ApiOperation(value = "添加商品", httpMethod = "POST")
    @RequestMapping(value = "v1/products", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addProduct(@RequestBody ProductVo productVo) {

        try {
            productService.addProduct(setProduct(productVo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加成功");
    }

    private MallProductWithBLOBs setProduct(ProductVo productVo) {

        MallProductWithBLOBs product = new MallProductWithBLOBs();
        product.setCategoryId(productVo.getCategoryId());
        product.setName(productVo.getName());
        product.setSubtitle(productVo.getSubtitle());
        product.setMainImage(productVo.getMainImage());
        product.setSubtitle(productVo.getSubtitle());
        product.setDetail(productVo.getDetail());
        product.setPrice(productVo.getPrice());
        product.setStock(productVo.getStock());
        product.setStatus(productVo.getStatus());
        if (productVo instanceof ProductUpdateVo) {
            product.setProductId(((ProductUpdateVo) productVo).getProductId());
        }

        return product;

    }

}
