package com.advert.smallapp.gannersql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsPushType extends BaseModel{
	
	private static final long serialVersionUID = 8166828705546832541L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	
	/**
	 * 推送类型 1-房间 2-麻将馆随机组局 3-房主 4-加入牌友圈
	 */
	private Integer pushType;
	
	/**
	 * 消息类型 1-通知 2-android自定义消息(通知) ios通知 3-广播消息 4-房间消息
	 */
	private Integer messageType;
	
	/**
	 * 推送文案
	 */
	private String pushText;
	
	/**
	 * 描述
	 */
	private String remark;
	
	/**
	 * 状态 1-正常 2-禁用
	 */
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPushType() {
		return pushType;
	}

	public void setPushType(Integer pushType) {
		this.pushType = pushType;
	}

	public String getPushText() {
		return pushText;
	}

	public void setPushText(String pushText) {
		this.pushText = pushText;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	
	
}
