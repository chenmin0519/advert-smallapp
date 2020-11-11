package com.advert.smallapp.gannersql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsPushConfig extends BaseModel {

	private static final long serialVersionUID = -4666118018467634889L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	
	/**
	 * appKey
	 */
	private String appKey;
	
	/**
	 * secret
	 */
	private String secret;
	
	/**
	 * 状态 0-删除  1-正常
	 */
	private Integer status;
	
	/**
	 * 游戏id
	 */
	private String gameId;
	
	/**
	 * appId
	 */
	private String appId;
	
	/**
	 * 游戏名
	 */
	private String gameName;
	
	/**
	 * app名称
	 */
	private String appName;

	/**
	 * 是否主appKey
	 */
	private Boolean master;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Boolean getMaster() {
		return master;
	}

	public void setMaster(Boolean master) {
		this.master = master;
	}
	
	
}
