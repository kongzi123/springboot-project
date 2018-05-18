package com.example.demo.common.response;

import java.io.Serializable;

/**
 * 接口返回对象
 * @author Administrator
 *
 */
public class ResponseEntity implements Serializable {

	private static final long serialVersionUID = -8982904182390842615L;

	private int code;
	
	private String message;
	
	private Object data = null;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResponseEntity() {
		super();
	}
	
	public ResponseEntity(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public ResponseEntity(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	/**
	 * 返回成功（返回不包含数据）
	 * @return
	 */
	public static ResponseEntity success() {
		return new ResponseEntity(ResponseCode.C200.getCode(), ResponseCode.C200.getMessage());
	}
	
	/**
	 * 返回成功（返回包含数据）
	 * @return
	 */
	public static ResponseEntity success(Object data) {
		return new ResponseEntity(ResponseCode.C200.getCode(), ResponseCode.C200.getMessage(), data);
	}
	
	/**
	 * 返回成功（指定reponsecode）
	 * @return
	 */
	public static ResponseEntity success(ResponseCode responseCode, Object data) {
		return new ResponseEntity(responseCode.getCode(), responseCode.getMessage(), data);
	}
	
	/**
	 * 返回成功（自定义code,message,data）
	 * @return
	 */
	public static ResponseEntity success(int code, String message, Object data) {
		return new ResponseEntity(code, message, data);
	}
	
	/**
	 * 返回失败
	 * @return
	 */
	public static ResponseEntity fail() {
		return new ResponseEntity(ResponseCode.C1000.getCode(), ResponseCode.C1000.getMessage());
	}
	
	/**
	 * 返回失败（指定message）
	 * @return
	 */
	public static ResponseEntity fail(String message) {
		return new ResponseEntity(ResponseCode.C1000.getCode(), message);
	}
	
	/**
	 * 返回失败（指定responsecode）
	 * @return
	 */
	public static ResponseEntity fail(ResponseCode responseCode, Object data) {
		return new ResponseEntity(responseCode.getCode(), responseCode.getMessage(), data);
	}
	
	/**
	 * 返回失败（自定义code,message,data）
	 * @return
	 */
	public static ResponseEntity fail(int code, String message, Object data) {
		return new ResponseEntity(code, message, data);
	}
	
}
