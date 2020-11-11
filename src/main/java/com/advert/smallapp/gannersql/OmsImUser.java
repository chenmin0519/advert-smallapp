package com.advert.smallapp.gannersql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsImUser extends BaseModel{
	
	private static final long serialVersionUID = 7975759475930791405L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	
	private Long gameUserId;
	
	private String imUserName;
	
	private String imPassword;
	
	private String appId;
	
	private Boolean canAdmin;
	
	private String imAdminUserName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImUserName() {
		return imUserName;
	}

	public void setImUserName(String imUserName) {
		this.imUserName = imUserName;
	}

	public String getImPassword() {
		return imPassword;
	}

	public void setImPassword(String imPassword) {
		this.imPassword = imPassword;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Long getGameUserId() {
		return gameUserId;
	}

	public void setGameUserId(Long gameUserId) {
		this.gameUserId = gameUserId;
	}

	public Boolean getCanAdmin() {
		return canAdmin;
	}

	public void setCanAdmin(Boolean canAdmin) {
		this.canAdmin = canAdmin;
	}

	public String getImAdminUserName() {
		return imAdminUserName;
	}

	public void setImAdminUserName(String imAdminUserName) {
		this.imAdminUserName = imAdminUserName;
	}
	
	
	
}
