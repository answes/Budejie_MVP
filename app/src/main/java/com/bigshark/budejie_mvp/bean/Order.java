package com.bigshark.budejie_mvp.bean;

/**
 * Created by bigShark on 2016/11/3.
 */

public class Order {

    private String state;
    private int count;
    private String tips;
    private int number;
    private String logoIco;
    private String storeName;
    private String sevierName;

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "state='" + state + '\'' +
                ", count=" + count +
                ", tips='" + tips + '\'' +
                ", number=" + number +
                ", logoIco='" + logoIco + '\'' +
                ", storeName='" + storeName + '\'' +
                ", sevierName='" + sevierName + '\'' +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLogoIco() {
        return logoIco;
    }

    public void setLogoIco(String logoIco) {
        this.logoIco = logoIco;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSevierName() {
        return sevierName;
    }

    public void setSevierName(String sevierName) {
        this.sevierName = sevierName;
    }
}
