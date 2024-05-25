package com.nlsapi.core.business.entity;

import java.util.Date;

public class MastMemberEntity {
    private Long mmId;

    private String mmAccount;

    private String mmPassword;

    private String mmNickname;

    private Date mmCreatedAt;

    private Date mmUpdatedAt;

    public Long getMmId() {
        return mmId;
    }

    public void setMmId(Long mmId) {
        this.mmId = mmId;
    }

    public String getMmAccount() {
        return mmAccount;
    }

    public void setMmAccount(String mmAccount) {
        this.mmAccount = mmAccount;
    }

    public String getMmPassword() {
        return mmPassword;
    }

    public void setMmPassword(String mmPassword) {
        this.mmPassword = mmPassword;
    }

    public String getMmNickname() {
        return mmNickname;
    }

    public void setMmNickname(String mmNickname) {
        this.mmNickname = mmNickname;
    }

    public Date getMmCreatedAt() {
        return mmCreatedAt;
    }

    public void setMmCreatedAt(Date mmCreatedAt) {
        this.mmCreatedAt = mmCreatedAt;
    }

    public Date getMmUpdatedAt() {
        return mmUpdatedAt;
    }

    public void setMmUpdatedAt(Date mmUpdatedAt) {
        this.mmUpdatedAt = mmUpdatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mmId=").append(mmId);
        sb.append(", mmAccount=").append(mmAccount);
        sb.append(", mmPassword=").append(mmPassword);
        sb.append(", mmNickname=").append(mmNickname);
        sb.append(", mmCreatedAt=").append(mmCreatedAt);
        sb.append(", mmUpdatedAt=").append(mmUpdatedAt);
        sb.append("]");
        return sb.toString();
    }
}