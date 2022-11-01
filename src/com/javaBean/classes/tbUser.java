package com.javaBean.classes;

/**
 * 基本用户表，附加status限制用户分为：普通用户，商家，管理员
 * 作为JavaBean实现用户表基本get和set
 */
public class tbUser {
    //用户编号
    private int id;
    //用户账户
    private String userAccount;
    //用户密码
    private String userPassword;
    //用户姓名
    private String userName;
    //用户性别: 1为男生，0为女生
    private int userSex;
    //用户权限: 0为管理员，1为普通用户，2为商家
    private int userStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
}
