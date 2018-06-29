package com.yyyu.mmall.controller.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.yyyu.mmall.controller.BaseController;
import com.yyyu.mmall.global.Constant;
import com.yyyu.mmall.uitls.codec.DESCoder;
import com.yyyu.mmall.uitls.controller.RestException;
import com.yyyu.mmall.uitls.controller.ResultUtils;
import com.yyyu.mmall.uitls.lang.StringUtils;
import com.yyyu.mmall.utils.TokenManager;
import com.yyyu.user.pojo.MallUser;
import com.yyyu.user.pojo.MallUserExample;
import com.yyyu.user.pojo.MallUserToken;
import com.yyyu.user.pojo.bean.TokenJwt;
import com.yyyu.user.pojo.result.LoginReturn;
import com.yyyu.user.pojo.vo.UserUpdateVo;
import com.yyyu.user.pojo.vo.UserVo;
import com.yyyu.user.service.inter.UserServiceInter;
import com.yyyu.user.service.inter.UserTokenServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 功能：用户相关Controller
 * @author yu
 * @date 2018/1/24.
 */
@Api(value = "user", description = "用户相关操作",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/user")
@Controller
public class UserController extends BaseController{

    @Autowired
    private UserServiceInter userService;

    @Autowired
    private UserTokenServiceInter userTokenService;

    @ApiOperation(value = "用户登录",
            httpMethod = "POST")
    @RequestMapping(value = "v1/user/login" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils login(@ApiParam(value = "用户名", required = true ) @RequestParam("username") String username ,
                             @ApiParam(value = "密码", required = true )@RequestParam("pwd") String pwd ,
                              HttpServletResponse response){

        LoginReturn loginReturn = new LoginReturn();

        try {
            List<MallUser> mallUsers = userService.selectByUsername(username);
            if (mallUsers==null|| mallUsers.size()<=0){
                return ResultUtils.createError(",用户名或密码错误");
            }
            MallUser mallUser = mallUsers.get(0);
            String password = mallUser.getPassword();
            if (!pwd.equals(password)){
                return ResultUtils.createError("用户名或密码错误");
            }
            loginReturn.setUserId(mallUser.getUserId());
            loginReturn.setUsername(mallUser.getUsername());
            loginReturn.setEmail(mallUser.getEmail());
            loginReturn.setPhone(mallUser.getPhone());
            loginReturn.setStatus(mallUser.getStatus());
            loginReturn.setCreateTime(mallUser.getCreateTime());
            loginReturn.setUpdateTime(mallUser.getUpdateTime());
            //生成token
            String token = TokenManager.getInstance().genToken(mallUser.getUserId());
            // 保存到数据库
          /*  MallUserToken userToken = new MallUserToken();
            userToken.setUserId(mallUser.getUserId());
            userToken.setSessionId(token);
            userTokenService.addUserToken(userToken);*/
            loginReturn.setToken(token);
            //加入到cookie缓存
            Cookie cookie = new Cookie(Constant.TOKEN, token);
            cookie.setPath("/");
            cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof RestException){
                return ResultUtils.createResult(((RestException) e).getCode() , ((RestException) e).getMsg());
            }
            return ResultUtils.createError(e.getMessage());
        }
        return ResultUtils.createSuccess(loginReturn);
    }

    @ApiOperation(value = "添加用户",
            notes = "传入json格式的用户信息的",
            httpMethod = "POST",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/user" , method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils addUser(@ApiParam(value = "用户信息", required = true ) @RequestBody UserVo userVo){

        try {

            //查看用户名是否已经存在
            List<MallUser> mallUsers = userService.selectByUsername(userVo.getUsername());
            if (mallUsers.size()>0){
                return ResultUtils.createError("该用户名已存在");
            }

            MallUser mallUser = new MallUser();
            mallUser.setUsername(userVo.getUsername());
            //todo 加密
            mallUser.setPassword(userVo.getPassword());
            mallUser.setPhone(userVo.getPhone());
            mallUser.setEmail(userVo.getEmail());
            mallUser.setStatus(userVo.getStatus());
            mallUser.setQuestion(userVo.getQuestion());
            // todo 加密
            mallUser.setAnswer(userVo.getAnswer());
            userService.addUser(mallUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("添加用户成功");
    }

    @ApiOperation(value = "批量删除用户",
            httpMethod = "POST",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/users:delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtils deleteUser(@RequestBody List<Long> userIdList){

        try {
            userService.reallyDeleteUserByIdList(userIdList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除成功");
    }

    @ApiOperation(value = "删除用户",
            httpMethod = "DELETE",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/users/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultUtils deleteUser(@ApiParam(value = "用户id"  , required = true) @PathVariable  Long userId){

        try {
            userService.reallyDeleteUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess("删除成功");
    }


    @ApiOperation(value = "修改用户",
            notes = "传入json格式的用户信息的，需要admin权限才能操作",
            httpMethod = "PATCH",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/user" , method = RequestMethod.PATCH )
    @ResponseBody
    public ResultUtils updateUser(@RequestBody UserUpdateVo userUpdateVo){

        try {
            //查看用户名是否已经存在
            List<MallUser> mallUsers = userService.selectByUsername(userUpdateVo.getUsername());
            if (mallUsers.size()>0){
                return ResultUtils.createError("该用户名已存在");
            }
            MallUser user = new MallUser();
            user.setUserId(userUpdateVo.getUserId());
            user.setUsername(userUpdateVo.getUsername());
            //---TODO 加密
            user.setPassword(userUpdateVo.getPassword());
            user.setStatus(userUpdateVo.getStatus());
            user.setPhone(userUpdateVo.getPhone());
            user.setEmail(userUpdateVo.getEmail());
            user.setQuestion(userUpdateVo.getQuestion());
            user.setAnswer(userUpdateVo.getAnswer());
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }
        MallUser mallUser = userService.selectByUserId(userUpdateVo.getUserId());
        return ResultUtils.createSuccess("修改用户信息成功",mallUser);
    }


    @ApiOperation(value = "根据Id获得用户信息",
            notes = "传入用户id",
            httpMethod = "GET",
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/users/{userId}",method = RequestMethod.GET)
    @JsonView(MallUser.UserReturn.class)
    @ResponseBody
    public ResultUtils  getUserById(@ApiParam(value = "用户id",  required = true) @PathVariable("userId")  Long userId){
        MallUser mallUser;
        try {
            mallUser = userService.selectByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallUser);
    }


    @ApiOperation(value = "分页获得用户信息",
            notes = "传入分页信息",
            httpMethod = "GET",
            response = ResultUtils.class,
            produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "v1/users", method = RequestMethod.GET)
    @JsonView(MallUser.UserDetail.class)
    @ResponseBody
    public ResultUtils getUserByPage(@ApiParam(value = "从第几行开始区数据", required = true) @RequestParam(defaultValue = "0") Integer start ,
                                     @ApiParam(value = "取数据的条数" , required = true)  @RequestParam(defaultValue = "10") Integer size ,
                                     HttpServletRequest request){

        PageInfo<MallUser> mallUserPageInfo;
        try {
            String sort = getParameterUtf8(request , "sort");
            String order =  getParameterUtf8(request , "order");
            String username =  getParameterUtf8(request , "username");
            MallUserExample mallUserExample = new MallUserExample();
            String orderByClause = genOrderByClause(sort, order);
            if (!StringUtils.isEmpty(orderByClause)){
                mallUserExample.setOrderByClause(orderByClause);
            }
            if(!StringUtils.isEmpty(username)){
                mallUserExample.createCriteria().andUsernameLike("%"+username+"%");
            }
            mallUserPageInfo = userService.selectUserByPage(start,size , mallUserExample);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.createError(e.getMessage());
        }

        return ResultUtils.createSuccess(mallUserPageInfo);
    }

    @RequestMapping(value = "v1/session/test", method = RequestMethod.GET)
    @JsonView(MallUser.UserDetail.class)
    @ResponseBody
    public ResultUtils getTokenJwt(){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(System.currentTimeMillis());
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE , 7);
        TokenJwt tokenJwt = new TokenJwt(2 + "", currentDate.getTime(), calendar.getTime().getTime());
        String tokenJwtStr = new Gson().toJson(tokenJwt);
        //System.out.println("tokenJwtStr："+tokenJwtStr);
        String encryptJwt = DESCoder.encrypt(tokenJwtStr, Constant.DES_KEY);
        //System.out.println("encryptJwt："+encryptJwt);
        return ResultUtils.createSuccess("获取数据成功" , encryptJwt);
    }


}
