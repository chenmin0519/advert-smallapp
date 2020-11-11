package com.advert.smallapp.gannersql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsCustomGameConfig extends BaseModel{

	private static final long serialVersionUID = -3563317613432972508L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	
	/**
	 * 客服id
	 */
	private Long customId;
	
	/**
	 * 是否开启客服
	 */
	private Boolean isCustom;
	
	/**
	 * 游戏id
	 */
	private String gameId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(Boolean isCustom) {
		this.isCustom = isCustom;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
	}
	
	
	
}
