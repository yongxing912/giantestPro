package com.yongxing912.model;


import java.util.Date;

public class Product {

    private int productId;
    private String productName;
    private String pnMask;
    private String pnSpec;
    private String pnSize;
    private String grossDie;
    private int customerId = -1;
    private Date joinDay;
    private String pnDescp;
    private String regTime;
    private String regUser;

    private String customerCode;

    public Product() {
    }

    public Product(String productName, String pnMask, String pnSpec, String pnSize, String grossDie, int customerId, Date joinDay, String pnDescp, String regTime, String regUser) {
        this.productName = productName;
        this.pnMask = pnMask;
        this.pnSpec = pnSpec;
        this.pnSize = pnSize;
        this.grossDie = grossDie;
        this.customerId = customerId;
        this.joinDay = joinDay;
        this.pnDescp = pnDescp;
        this.regTime = regTime;
        this.regUser = regUser;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getPnMask() {
        return pnMask;
    }

    public void setPnMask(String pnMask) {
        this.pnMask = pnMask;
    }


    public String getPnSpec() {
        return pnSpec;
    }

    public void setPnSpec(String pnSpec) {
        this.pnSpec = pnSpec;
    }


    public String getPnSize() {
        return pnSize;
    }

    public void setPnSize(String pnSize) {
        this.pnSize = pnSize;
    }


    public String getGrossDie() {
        return grossDie;
    }

    public void setGrossDie(String grossDie) {
        this.grossDie = grossDie;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public Date getJoinDay() {
        return joinDay;
    }

    public void setJoinDay(Date joinDay) {
        this.joinDay = joinDay;
    }


    public String getPnDescp() {
        return pnDescp;
    }

    public void setPnDescp(String pnDescp) {
        this.pnDescp = pnDescp;
    }


    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }


    public String getRegUser() {
        return regUser;
    }

    public void setRegUser(String regUser) {
        this.regUser = regUser;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
