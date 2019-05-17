/**
 * 
 */
package com.qq.common;

import java.util.Vector;

public class Message implements java.io.Serializable{
	/**
	 * MessType  1->表明登陆成功
	 * MessType  2->表明登录失败
	 * MessType  3->表示是普通消息
	 */
	private String mesType;
	
	private String getter;
	//内容
	private String con;
	private Vector vector;
	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}

	private String sendTime;
	private String sender;
	private boolean stage;
	
	public boolean isStage() {
		return stage;
	}

	public void setStage(boolean stage) {
		this.stage = stage;
	}

	private String []userRegister;
	

	public String[] getUserRegister() {
		return userRegister;
	}

	public void setUserRegister(String[] userRegister) {
		this.userRegister = userRegister;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
}
