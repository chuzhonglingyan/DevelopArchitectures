package com.yuntian.dragger2databinding.net.entity;

/**
 * description .
 * Created by ChuYingYan on 2018/5/1.
 */
public class LoginPsdParam extends BaseEntity {



    private String mobile;
    private String password;
    private String verifyCode;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
