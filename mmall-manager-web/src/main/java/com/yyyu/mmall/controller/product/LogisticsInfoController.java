package com.yyyu.mmall.controller.product;

import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.product.pojo.MallLogisticsInfo;
import com.yyyu.product.pojo.vo.LogisticsInfoUpdateVo;
import com.yyyu.product.pojo.vo.LogisticsInfoVo;
import com.yyyu.product.service.inter.LogisticsInfoServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能：物流信息相关api
 *
 * @author yu
 * @date 2018/2/1.
 */
@Api(value = "logistics_info", description = "物流信息相关操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/logistics_info")
@Controller
public class LogisticsInfoController extends BaseController{

    @Autowired
    private LogisticsInfoServiceInter logisticsInfoService;

    @ApiOperation(value = "查询某一用户的所有物流信息", httpMethod = "GET")
    @RequestMapping(value = "v1/logistics/users/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultUtils updateLogistics(@ApiParam(value = "用户Id", required = true) @PathVariable Long userId) {

        List<MallLogisticsInfo> mallLogisticsInfos;
        try {
            mallLogisticsInfos = logisticsInfoService.selectAllLogisticsInfoByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallLogisticsInfos);
    }

    @ApiOperation(value = "更新物流信息", httpMethod = "PATCH")
    @RequestMapping(value = "v1/logistics", method = RequestMethod.PATCH)
    @ResponseBody
    public ResultUtils updateLogistics(@RequestBody LogisticsInfoUpdateVo logisticsInfoUpdateVo) {

        try {
            logisticsInfoService.updateLogisticsInfo(setLogisticsInfo(logisticsInfoUpdateVo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("更新成功");
    }

    @ApiOperation(value = "删除物流信息", httpMethod = "DELETE")
    @RequestMapping(value = "v1/logistics/{logisticsId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultUtils deleteLogisticsByLogisticsId(@ApiParam(value = "物流信息Id", required = true) @PathVariable Long logisticsId) {

        try {
            logisticsInfoService.deleteLogisticsInfoByLogisticsId(logisticsId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除成功");
    }

    @ApiOperation(value = "添加物流信息", httpMethod = "POST")
    @RequestMapping(value = "v1/logistics", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addLogistics(@RequestBody LogisticsInfoVo logisticsInfoVo) {

        try {
            logisticsInfoService.addLogisticsInfo(setLogisticsInfo(logisticsInfoVo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加成功");
    }

    private MallLogisticsInfo setLogisticsInfo(LogisticsInfoVo logisticsInfoVo) {

        MallLogisticsInfo logisticsInfo = new MallLogisticsInfo();
        logisticsInfo.setUserId(logisticsInfoVo.getUserId());
        logisticsInfo.setReceiverName(logisticsInfoVo.getReceiverName());
        logisticsInfo.setReceiverTel(logisticsInfoVo.getReceiverTel());
        logisticsInfo.setReceiverPhone(logisticsInfoVo.getReceiverPhone());
        logisticsInfo.setReceiverProvince(logisticsInfoVo.getReceiverProvince());
        logisticsInfo.setReceiverCity(logisticsInfoVo.getReceiverCity());
        logisticsInfo.setReceiverDistrict(logisticsInfoVo.getReceiverDistrict());
        logisticsInfo.setReceiverAddress(logisticsInfoVo.getReceiverAddress());
        logisticsInfo.setReceiverZipCode(logisticsInfoVo.getReceiverZipCode());
        if (logisticsInfoVo instanceof LogisticsInfoUpdateVo) {
            logisticsInfo.setLogisticsId(((LogisticsInfoUpdateVo) logisticsInfoVo).getLogisticsId());
        }

        return logisticsInfo;
    }

}
