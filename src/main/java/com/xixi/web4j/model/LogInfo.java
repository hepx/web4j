package com.xixi.web4j.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志信息
 * @author hepx
 *
 */
public class LogInfo implements Serializable {

	private static final long serialVersionUID = 6711467427091250567L;

	private Integer logId;
	
	private String operator;
	
	private String msg;
	
	private Date logTime;
	
	public LogInfo(){}
	
	public LogInfo(String operator,String msg){
		this.operator=operator;
		this.msg=msg;
		this.logTime=new Date();
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
	
	
}
