package com.vaadin.demo.dashboard.domain;

import java.util.Date;

public final class Transaction {
	private int id;
	private int sequenceId;
	private Date dateTime;
	private String moduleName;
	private String refId;
	private String opCode;
	private String direction;
	private String serviceId;
	private String msisdn;
	private String cellId;
	private String vlrId;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSequenceId() {
		return sequenceId;
	}
	
	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}
	
	public Date getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	public String getModuleName() {
		return moduleName;
	}
	
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public String getRefId() {
		return refId;
	}
	
	public void setRefId(String refId) {
		this.refId = refId;
	}
	
	public String getOpCode() {
		return opCode;
	}
	
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getServiceId() {
		return serviceId;
	}
	
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getMsisdn() {
		return msisdn;
	}
	
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	public String getCellId() {
		return cellId;
	}
	
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	
	public String getVlrId() {
		return vlrId;
	}
	
	public void setVlrId(String vlrId) {
		this.vlrId = vlrId;
	}
}
