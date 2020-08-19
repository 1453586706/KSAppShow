package com.example.kuaishouappshow.entity;

public class SelfansEntity {

    /**
     * headImg : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575535483679&di=ca4b60a00387e29c8c230ef818cefb71&imgtype=0&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Farchive%2F85af4533da5c4552ee0b60edba40838f8588ee99.jpg
     * relationId : 590
     * followId : 540
     * observerableId : 697
     * userId : 540
     * phoneNum : 11412
     */

    private String headImg;
    private long relationId;
    private long followId;
    private long observerableId;
    private long userId;
    private long phoneNum;

    public SelfansEntity(String headImg, int relationId, int followId, int observerableId, int userId, long phoneNum) {
        this.headImg = headImg;
        this.relationId = relationId;
        this.followId = followId;
        this.observerableId = observerableId;
        this.userId = userId;
        this.phoneNum = phoneNum;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public long getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public long getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public long getObserverableId() {
        return observerableId;
    }

    public void setObserverableId(int observerableId) {
        this.observerableId = observerableId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }
}
