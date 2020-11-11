package com.advert.smallapp.gannersql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsCustomQuickMessage  extends BaseModel{
	
	private static final long serialVersionUID = 1492532536880998589L;
	
	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	
	/**
	 * 客服id
	 */
	private Long customId;
	
	/**
	 * 消息
	 */
	private String message;
	
	/**
	 * 状态(0-禁用 1-启用)
	 */
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
