package com.jiachenzh.entity;

;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @ClassName VerifyCodePO
 * @Description TODO
 * @Author
 * @Date 2019/2/28 19:55
 * @Version 1.0
 **/
public class VerificationCodePO {
    @Email(message = "邮箱格式错误")
    private String email;
    @NotNull(message = "error：发送验证码类型为null")
    private Integer verificationCodeType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getVerificationCodeType() {
        return verificationCodeType;
    }

    public void setVerificationCodeType(Integer verificationCodeType) {
        this.verificationCodeType = verificationCodeType;
    }
}
