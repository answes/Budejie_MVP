package com.bigshark.budejie_mvp.bean;

/**
 * Created by bigShark on 2017/1/2.
 */

public class Record {
    private String address;
    private String cityName;
    private String code;
    private String degree;
    private String department;
    private String money;
    private String reason;
    private String time;

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setCityName(String cityName){
        this.cityName = cityName;
    }
    public String getCityName(){
        return this.cityName;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setDegree(String degree){
        this.degree = degree;
    }
    public String getDegree(){
        return this.degree;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public String getDepartment(){
        return this.department;
    }
    public void setMoney(String money){
        this.money = money;
    }
    public String getMoney(){
        return this.money;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
    public String getReason(){
        return this.reason;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getTime(){
        return this.time;
    }
}
