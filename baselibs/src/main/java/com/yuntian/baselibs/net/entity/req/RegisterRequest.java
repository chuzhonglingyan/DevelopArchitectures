package com.yuntian.baselibs.net.entity.req;

/**
 * @author guangleilei
 * @version 1.0 2017-04-01
 */
public class RegisterRequest extends BaseRequest {



    private String mobile;
    private String name;
    private String password;
    private String verifyCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
