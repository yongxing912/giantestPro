package com.yongxing912.model;


public class Customer {

  private int customerId;
  private String customerName;
  private String customerCode;
  private String contacts;
  private String phone;
  private String fax;
  private String email;
  private String address;
  private String customerDescp;
  private String regTime;
  private String regUser;

    public Customer() {
    }

    public Customer(String customerName, String customerCode, String contacts, String phone, String fax, String email, String address, String customerDescp, String regTime, String regUser) {
        this.customerName = customerName;
        this.customerCode = customerCode;
        this.contacts = contacts;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.address = address;
        this.customerDescp = customerDescp;
        this.regTime = regTime;
        this.regUser = regUser;
    }

    public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }


  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }


  public String getCustomerCode() {
    return customerCode;
  }

  public void setCustomerCode(String customerCode) {
    this.customerCode = customerCode;
  }


  public String getContacts() {
    return contacts;
  }

  public void setContacts(String contacts) {
    this.contacts = contacts;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getCustomerDescp() {
    return customerDescp;
  }

  public void setCustomerDescp(String customerDescp) {
    this.customerDescp = customerDescp;
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

}
