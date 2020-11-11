package com.advert.smallapp.gannersql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsPushHistory extends BaseModel{
	
	private static final long serialVersionUID = 4682778262018477543L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	
	/**
	 * 极光appKey
	 */
	private String appKey;
	
	/**
	 * 1-根据gameId推送 2-根据appName推送
	 */
	private Integer pushType;
	
	/**
	 * gameId/appName
	 */
	private String pushCode;
	
	/**
	 * 平台 1-all 2-android 3-ios
	 */
	private Integer platform;
	
	/**
	 * 标签
	 */
	private String tags;
	
	/**
	 * 别名
	 */
	private String alias;
	
	/**
	 * 通知alert
	 */
	private String alert;
	
	/**
	 * 通知标题
	 */
	private String title;
	
	/**
	 * 消息内容
	 */
	private String msg;
	
	/**
	 * 推送结果
	 */
	private Boolean pushResult;
	
	/**
	 * 推送msgId
	 */
	private Long messageId;
	
	private String errorCode;
	
	private String errorMsg;
	
	/**
	 * 事件类型
	 */
	private Integer eventType;
	
	/**
	 * 事件id
	 */
	private String eventId;

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

	public String getPushCode() {
		return pushCode;
	}

	public void setPushCode(String pushCode) {
		this.pushCode = pushCode;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getPushResult() {
		return pushResult;
	}

	public void setPushResult(Boolean pushResult) {
		this.pushResult = pushResult;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	
}
