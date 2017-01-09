package com.bigshark.budejie_mvp.bean;//
//	ShowapiResBody.java
//	Model file Generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport



public class ShowapiResBody{
	private String carCode;//7
	private String carEngineCode;//4
	private String carNumber;//5
	private String carType;//12
	private int count;//3
	private long createDate;//13
	private String createDateStr;//11
	private int errorCode;//6
	private String errorMsg;//8
	private boolean flag;//2
	private String msg;//10
	private Record[] records;//9
	private String retCode;//1

	public void setCarCode(String carCode){
		this.carCode = carCode;
	}
	public String getCarCode(){
		return this.carCode;
	}
	public void setCarEngineCode(String carEngineCode){
		this.carEngineCode = carEngineCode;
	}
	public String getCarEngineCode(){
		return this.carEngineCode;
	}
	public void setCarNumber(String carNumber){
		this.carNumber = carNumber;
	}
	public String getCarNumber(){
		return this.carNumber;
	}
	public void setCarType(String carType){
		this.carType = carType;
	}
	public String getCarType(){
		return this.carType;
	}
	public void setCount(int count){
		this.count = count;
	}
	public int getCount(){
		return this.count;
	}
	public void setCreateDate(long createDate){
		this.createDate = createDate;
	}
	public long getCreateDate(){
		return this.createDate;
	}
	public void setCreateDateStr(String createDateStr){
		this.createDateStr = createDateStr;
	}
	public String getCreateDateStr(){
		return this.createDateStr;
	}
	public void setErrorCode(int errorCode){
		this.errorCode = errorCode;
	}
	public int getErrorCode(){
		return this.errorCode;
	}
	public void setErrorMsg(String errorMsg){
		this.errorMsg = errorMsg;
	}
	public String getErrorMsg(){
		return this.errorMsg;
	}
	public void setFlag(boolean flag){
		this.flag = flag;
	}
	public boolean isFlag()
	{
		return this.flag;
	}
	public void setMsg(String msg){
		this.msg = msg;
	}
	public String getMsg(){
		return this.msg;
	}
	public void setRecords(Record[] records){
		this.records = records;
	}
	public Record[] getRecords(){
		return this.records;
	}
	public void setRetCode(String retCode){
		this.retCode = retCode;
	}
	public String getRetCode(){
		return this.retCode;
	}



}