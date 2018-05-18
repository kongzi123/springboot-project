package com.example.demo.common.response;

/**
 * 返回的编码
 * @author Administrator
 *
 */
public enum ResponseCode {
	
	C200(200, "操作成功"),
	C1000(1000, "操作失败"),
	C1001(1001, "参数不满足规则"),
	C1002(1002, "查询内容为空");
	
	private int code;
	
	private String message;

	private ResponseCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
