package com.yuntian.dragger2databinding.mvp.ui.user.bean;

/**
 * description .
 * Created by ChuYingYan on 2018/5/1.
 */
public class UserInfoBean {

    private String age;
    private String genderCode;
    private String headUrl;
    private String sex;


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "UserInfoBean{" +
                "age='" + age + '\'' +
                ", genderCode='" + genderCode + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
