package com.advert.smallapp.gannersql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsChatTag extends BaseModel{
	
	private static final long serialVersionUID = -2095960261029034107L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long tagId;
	
	/**
	 * code
	 */
	private String code;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 显示信息
	 */
	private String message;
	
	/**
	 * 类型(1-违规标记)
	 */
	private Integer type;
	
	/**
	 * 状态 0-启用 1-禁用
	 */
	private Integer status;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	
}
