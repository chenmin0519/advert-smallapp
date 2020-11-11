package com.advert.smallapp.gannersql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsCustomOnlineHistory extends BaseModel{
	
	private static final long serialVersionUID = -73538667178573447L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	
	/**
	 * 客服id
	 */
	private Long customId;
	
	/**
	 * 操作人id
	 */
	private Long operatorId;
	
	/**
	 * 上线
	 */
	private Boolean online;

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

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}
	
	
	
}
