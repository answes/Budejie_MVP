package com.bigshark.budejie_mvp.bean;

/**
 * Created by bigShark on 2016/12/31.
 */

public class Illegal {
    private ShowapiResBody showapiResBody;
    private int showapiResCode;
    private String showapiResError;

    public void setShowapiResBody(ShowapiResBody showapiResBody){
        this.showapiResBody = showapiResBody;
    }
    public ShowapiResBody getShowapiResBody(){
        return this.showapiResBody;
    }
    public void setShowapiResCode(int showapiResCode){
        this.showapiResCode = showapiResCode;
    }
    public int getShowapiResCode(){
        return this.showapiResCode;
    }
    public void setShowapiResError(String showapiResError){
        this.showapiResError = showapiResError;
    }
    public String getShowapiResError(){
        return this.showapiResError;
    }

}
