package com.yuntian.dragger2databinding.mvp.ui.user.bean;

/**
 * @author guangleilei
 * @version 1.0 2017-04-01
 */
public class UserInfoBean {

    /**
     * age : 200
     * createUserId : 0
     * delFlg : 0
     * genderCode : 1
     * headUrl : def
     * id : 1
     * memberId : 1
     * mobile : 15827544776
     * nickName : 卧槽
     * realName : ren
     * sex : 女
     * ucId : 65202
     * ucenterId : 65202
     * updateUserId : 0
     * userName : ren
     * userStatus : 0
     */
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
