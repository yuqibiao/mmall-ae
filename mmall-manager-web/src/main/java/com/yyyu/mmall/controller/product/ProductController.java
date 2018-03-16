package com.yyyu.mmall.controller.product;

import com.github.pagehelper.PageInfo;
import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.mmall.uitls.lang.StringUtils;
import com.yyyu.product.pojo.*;
import com.yyyu.product.pojo.vo.ProductUpdateVo;
import com.yyyu.product.pojo.vo.ProductVo;
import com.yyyu.product.service.inter.ProductCategoryServiceInter;
import com.yyyu.product.service.inter.ProductServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @Autowired
    private ProductCategoryServiceInter categoryService;

    @Value("${server.address}")
    private String serverAddress;

    @ApiOperation(value = "分页查询某一分类下的商品商品", httpMethod = "GET")
    @RequestMapping(value = "v1/products/categories/{categoryId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getProductPageByCategoryId(@ApiParam(value = "商品分类Id", required = true) @PathVariable Long categoryId,
                                                  @ApiParam(value = "从第几行开始区数据") @RequestParam(defaultValue = "0") Integer start,
                                                  @ApiParam(value = "取数据的条数") @RequestParam(defaultValue = "10") Integer size,
                                                  HttpServletRequest request) {

        PageInfo<MallProduct> mallProductPageInfo;
        try {
            String sort = getParameterUtf8(request , "sort");
            String order =  getParameterUtf8(request , "order");
            String productName = getParameterUtf8(request , "name");
            MallProductExample mallProductExample = new MallProductExample();
            String orderByClause = genOrderByClause(sort, order);
            if (!StringUtils.isEmpty(orderByClause)){
                mallProductExample.setOrderByClause(orderByClause);
            }
            if(!StringUtils.isEmpty(productName)){
                mallProductExample.createCriteria().andNameLike("%"+productName+"%");
            }
            mallProductPageInfo = productService.selectProductPageByCategoryId(categoryId, start, size , mallProductExample);
            for (int i=0 ; i<mallProductPageInfo.getList().size();i++){
                String mainImage = mallProductPageInfo.getList().get(i).getMainImage();
                mallProductPageInfo.getList().get(i).setMainImage(serverAddress+mainImage);
            }
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

        MallProductReturn mallProductReturn = new MallProductReturn();
        try {
            //--TODO 返回图片服务器地址 、返回商品分类名称
            MallProductWithBLOBs product = productService.selectMallProductByProductId(productId);
            if (product==null){
                return ResultUtils.createSuccess(mallProductReturn);
            }
            Long categoryId = product.getCategoryId();
            MallProductCategory category = categoryService.selectProductCategoryByCategoryId(categoryId);
            mallProductReturn.setCategoryId(categoryId);
            mallProductReturn.setCategoryName(category.getName());
            mallProductReturn.setServerAddress(serverAddress);
            mallProductReturn.setProductId(product.getProductId());
            mallProductReturn.setName(product.getName());
            mallProductReturn.setMainImage(product.getMainImage());
            mallProductReturn.setPrice(product.getPrice());
            mallProductReturn.setSubtitle(product.getSubtitle());
            mallProductReturn.setStock(product.getStock());
            mallProductReturn.setStatus(product.getStatus());
            mallProductReturn.setSubImage(product.getSubImage());
            String detail = product.getDetail().replaceAll("<img src=\"", "<img src=\"" + "" + serverAddress);
            mallProductReturn.setDetail(detail);
            mallProductReturn.setCreateTime(product.getCreateTime());
            mallProductReturn.setUpdateTime(product.getUpdateTime());

        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallProductReturn);
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

    @ApiOperation(value = "根据Id集合批量删除商品", httpMethod = "POST")
    @RequestMapping(value = "v1/products:delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils deleteProductByProductIdList(@ApiParam(value = "商品Id集合") @RequestBody List<Long> productIdList) {

        try {
            productService.deleteProductByProductIdList(productIdList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除成功");
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
