package com.yuntian.dragger2databinding.ui.user.bean;

/**
 * @author guangleilei
 * @version 1.0 2017-04-01
 */
public class TokenBean {

    private String memberId;
    private String token;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "TokenBean{" +
                "memberId='" + memberId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
