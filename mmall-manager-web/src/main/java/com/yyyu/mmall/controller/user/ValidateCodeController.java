package com.yyyu.mmall.controller.user;

import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.uitls.controller.validate_code.ValidateCode;
import com.yyyu.mmall.uitls.lang.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.yyyu.mmall.uitls.controller.validate_code.ValidateCode.VALIDATE_CODE_SESSION;

/**
 * 功能：验证码
 *
 * @author yu
 * @version 1.0
 * @date 2018/7/31
 */
@Api(value = "validateCode", description = "验证码相关操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/validateCode")
@Controller
public class ValidateCodeController extends BaseController {

    @ApiOperation(value = "生成验证码", httpMethod = "GET")
    @RequestMapping(value = "generate", method = RequestMethod.GET)
    public String generateValidateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        //response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        ValidateCode vCode = new ValidateCode(120, 40, 5, 100);
        session.setAttribute(VALIDATE_CODE_SESSION, vCode.getCode());
        vCode.write(response.getOutputStream());

        String sessionCode = (String) session.getAttribute(VALIDATE_CODE_SESSION);
        return null;
    }

}
