package com.yyyu.mmall.controller.product;

import com.github.pagehelper.PageInfo;
import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.mmall.uitls.lang.StringUtils;
import com.yyyu.product.pojo.MallProductCategory;
import com.yyyu.product.pojo.MallProductCategoryExample;
import com.yyyu.product.pojo.MallProductCategoryWithParentName;
import com.yyyu.product.pojo.vo.ProductCategoryUpdateVo;
import com.yyyu.product.pojo.vo.ProductCategoryVo;
import com.yyyu.product.service.inter.ProductCategoryServiceInter;
import com.yyyu.user.pojo.bean.ZTreeNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：商品分类api
 *
 * @author yu
 * @date 2018/1/30.
 */
@Api(value = "product_category" , description = "商品分类相关",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/product")
@Controller
public class ProductCategoryController extends BaseController{

    @Autowired
    private ProductCategoryServiceInter productCategoryService;

    @ApiOperation(value = "查询所有商品分类" , httpMethod = "GET")
    @RequestMapping(value = "v1/categories:all" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getAllProductCategory(){
        List<ZTreeNode> zTreeNodeList;
        try {

            List<MallProductCategory> categoryList=  productCategoryService.selectAllProductCategory();
            zTreeNodeList = new ArrayList<>();
            for (MallProductCategory category:categoryList) {
                ZTreeNode zTreeNode = new ZTreeNode();
                zTreeNode.setId(category.getCategoryId());
                zTreeNode.setpId(category.getParentId());
                zTreeNode.setOpen(true);
                zTreeNode.setName(category.getName());
                zTreeNodeList.add(zTreeNode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(zTreeNodeList);
    }

    @ApiOperation(value = "根据Id查询商品分类" , httpMethod = "GET")
    @RequestMapping(value = "v1/categories/{categoryId}" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getCategoryById(@ApiParam(value = "分类Id" ,required = true) @PathVariable Long categoryId){

        MallProductCategoryWithParentName categoryWithParentName = new MallProductCategoryWithParentName();
        try {
            MallProductCategory category = productCategoryService.selectProductCategoryByCategoryId(categoryId);
            Long parentId = category.getParentId();
            MallProductCategory parentCategory  = productCategoryService.selectProductCategoryByCategoryId(parentId);
            if(parentId!=null){
                categoryWithParentName.setParentName(parentCategory.getName());
            }
            categoryWithParentName.setCategoryId(category.getCategoryId());
            categoryWithParentName.setName(category.getName());
            categoryWithParentName.setCode(category.getCode());
            categoryWithParentName.setStatus(category.getStatus());
            categoryWithParentName.setCreateTime(category.getCreateTime());
            categoryWithParentName.setParentId(category.getParentId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(categoryWithParentName);
    }

    @ApiOperation(value = "分页查询商品分类" , httpMethod = "GET")
    @RequestMapping(value = "v1/categories" , method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils getProductCategoryByPage(@ApiParam(value = "从第几行开始区数据")  @RequestParam(defaultValue = "0") Integer start ,
                                             @ApiParam(value = "取数据的条数") @RequestParam(defaultValue = "10")  Integer size,
                                                HttpServletRequest request){

        PageInfo<MallProductCategory> pageInfo;
        try {
            String sort = getParameterUtf8(request , "sort");
            String order =  getParameterUtf8(request , "order");
            String categoryName = getParameterUtf8(request , "name");
            MallProductCategoryExample productCategoryExample = new MallProductCategoryExample();
            String orderByClause = genOrderByClause(sort, order);
            if (!StringUtils.isEmpty(orderByClause)){
                productCategoryExample.setOrderByClause(orderByClause);
            }
            if(!StringUtils.isEmpty(categoryName)){
                productCategoryExample.createCriteria().andNameLike("%"+categoryName+"%");
            }
            pageInfo=  productCategoryService.selectProductCategoryByPage(start , size , productCategoryExample);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(pageInfo);
    }

    @ApiOperation(value = "更新商品分类" , httpMethod = "PATCH")
    @RequestMapping(value = "v1/categories" , method = RequestMethod.PATCH)
    @ResponseBody
    public ResultUtils updateProductCategory(@RequestBody ProductCategoryUpdateVo productCategoryUpdateVo){

        try {
            productCategoryService.updateProductCategory(setCategory(productCategoryUpdateVo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("更新成功");
    }

    @ApiOperation(value = "根据分类Id集合批量删除商品分类" , httpMethod = "POST")
    @RequestMapping(value = "v1/categories:delete" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils deleteProductCategoryByIdList(@RequestBody @ApiParam(value = "分类Id集合" , required = true)   List<Long> categoryIdList){

        try {
            productCategoryService.deleteProductCategoryIdList(categoryIdList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除成功");
    }

    @ApiOperation(value = "根据分类Id删除商品分类" , httpMethod = "DELETE")
    @RequestMapping(value = "v1/categories/{categoryId}" , method = RequestMethod.DELETE)
    @ResponseBody
    public ResultUtils deleteProductCategoryById(@ApiParam(value = "分类Id" , required = true)@PathVariable  Long categoryId){

        try {
            productCategoryService.reallyDeleteProductCategory(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除成功");
    }

    @ApiOperation(value = "添加商品分类" , httpMethod = "POST")
    @RequestMapping(value = "v1/categories" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addProductCategory(@RequestBody ProductCategoryVo categoryVo){

        try {
            productCategoryService.addProductCategory(setCategory(categoryVo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加成功");
    }

    private MallProductCategory setCategory(ProductCategoryVo categoryVo){

        MallProductCategory category = new MallProductCategory();
        category.setParentId(categoryVo.getParentId());
        category.setName(categoryVo.getName());
        category.setStatus(categoryVo.getStatus());
        category.setCode(categoryVo.getCode());
        if(categoryVo instanceof ProductCategoryUpdateVo){
            category.setCategoryId(((ProductCategoryUpdateVo) categoryVo).getCategoryId());
        }

        return category;
    }

}
