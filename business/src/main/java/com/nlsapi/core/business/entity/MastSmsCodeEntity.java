package com.nlsapi.core.business.entity;

import java.util.Date;

public class MastSmsCodeEntity {
    private Long mscId;

    private String mscAccount;

    private String mscAccountType;

    private String mscCode;

    private String mscUsage;

    private String mscStatus;

    private Date mscCreatedAt;

    private Date mscUpdatedAt;

    public Long getMscId() {
        return mscId;
    }

    public void setMscId(Long mscId) {
        this.mscId = mscId;
    }

    public String getMscAccount() {
        return mscAccount;
    }

    public void setMscAccount(String mscAccount) {
        this.mscAccount = mscAccount;
    }

    public String getMscAccountType() {
        return mscAccountType;
    }

    public void setMscAccountType(String mscAccountType) {
        this.mscAccountType = mscAccountType;
    }

    public String getMscCode() {
        return mscCode;
    }

    public void setMscCode(String mscCode) {
        this.mscCode = mscCode;
    }

    public String getMscUsage() {
        return mscUsage;
    }

    public void setMscUsage(String mscUsage) {
        this.mscUsage = mscUsage;
    }

    public String getMscStatus() {
        return mscStatus;
    }

    public void setMscStatus(String mscStatus) {
        this.mscStatus = mscStatus;
    }

    public Date getMscCreatedAt() {
        return mscCreatedAt;
    }

    public void setMscCreatedAt(Date mscCreatedAt) {
        this.mscCreatedAt = mscCreatedAt;
    }

    public Date getMscUpdatedAt() {
        return mscUpdatedAt;
    }

    public void setMscUpdatedAt(Date mscUpdatedAt) {
        this.mscUpdatedAt = mscUpdatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mscId=").append(mscId);
        sb.append(", mscAccount=").append(mscAccount);
        sb.append(", mscAccountType=").append(mscAccountType);
        sb.append(", mscCode=").append(mscCode);
        sb.append(", mscUsage=").append(mscUsage);
        sb.append(", mscStatus=").append(mscStatus);
        sb.append(", mscCreatedAt=").append(mscCreatedAt);
        sb.append(", mscUpdatedAt=").append(mscUpdatedAt);
        sb.append("]");
        return sb.toString();
    }
}