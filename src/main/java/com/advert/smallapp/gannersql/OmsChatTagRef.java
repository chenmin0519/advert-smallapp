package com.advert.smallapp.gannersql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsChatTagRef extends BaseModel{
	
	private static final long serialVersionUID = -5875510559740460187L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	
	/**
	 * 标签id
	 */
	private Long tagId;
	
	/**
	 * 消息id
	 */
	private Long chatId;
	
	/**
	 * 会话id
	 */
	private Long cid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}
	
	
	
}
