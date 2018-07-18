package com.yyyu.user.pojo.vo;

import com.yyyu.mmall.uitls.controller.validator.custom.Forbidden;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * 功能：用户请求参数
 *
 * @author yu
 * @date 2018/1/26.
 */
public class UserVo {

    @NotEmpty(message = "{user.name.null}")
    @Forbidden()
    @ApiModelProperty(value = "用户名" ,required = true)
    private String username;

    @Length(min = 5, max = 20, message = "{user.pwd.length.illegal}")
    @ApiModelProperty(value = "密码" ,required = true)
    private String password;

    @NotEmpty
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户状态 1-可用 2-禁止")
    private Short status;

    @ApiModelProperty(value = "密保问题")
    private String question;

    @ApiModelProperty(value = "密保答案")
    private String answer;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
