package com.example.kuaishouappshow.entity;

/**
 *        {
 *   "calendar": "",
 *   "introduction": "",
 *   "userId": 1,
 *   "headImg": "",
 *   "sex": "",
 *   "userAddress": "",
 *   "phoneNum": 1234,
 *   "userPassWord": "1234"
 * }
 *
 */

public class LoginUserEntity {

    private String calendar;
    private String introduction;
    private long userId;
    private String headImg;
    private String sex;
    private String userAddress;
    private long phoneNum;
    private String userPassWord;
    public String getCalendar() {
        return calendar;
    }
    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getHeadImg() {
        return headImg;
    }
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    public long getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getUserPassWord() {
        return userPassWord;
    }
    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }
}
