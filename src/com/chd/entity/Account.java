package com.chd.entity;

import java.io.Serializable;

/**
 * 账户实体类
 */
public class Account implements Serializable {
    private String name;
    private String password;
    private int cash;

    /**
     * 带参构造函数
     */
    public Account(String name, String password, int cash) {
        this.name = name;
        this.password = password;
        this.cash = cash;
    }

    /**
     * 无参构造函数
     */
    public Account() {}

    /**
     * getter and setter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", cash=" + cash +
                '}';
    }
}
