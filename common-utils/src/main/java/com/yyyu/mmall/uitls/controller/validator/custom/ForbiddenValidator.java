package com.yyyu.mmall.uitls.controller.validator.custom;

import com.yyyu.mmall.uitls.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 功能：
 *
 * @author yu
 * @version 1.0
 * @date 2018/7/18
 */
public class ForbiddenValidator implements ConstraintValidator<Forbidden, String> {

    private String[] forbiddenWords = {"admin", "fuck"};

    @Override
    public void initialize(Forbidden forbidden) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        for (String word : forbiddenWords) {
            if (value.contains(word)) {
                return false;//验证失败
            }
        }
        return true;
    }
}
