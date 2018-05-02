package com.yuntian.baselibs.net.entity.req;

/**
 * @author guangleilei
 * @version 1.0 2017-04-01
 */
public class LoginPsdRequest extends BaseRequest {




    private String token;
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


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
