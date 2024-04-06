package com.teja.model;

public class CustomResponse {
	private Object data;
	 private String status;
	  private Integer statusCode;
	public CustomResponse() {
		super();
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public CustomResponse(Object data, String status, Integer statusCode) {
		super();
		this.data = data;
		this.status = status;
		this.statusCode = statusCode;
	}
	
}
