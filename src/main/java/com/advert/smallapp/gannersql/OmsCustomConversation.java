package com.advert.smallapp.gannersql;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class OmsCustomConversation extends BaseModel{
	
	private static final long serialVersionUID = 1495957990699007130L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long conversationId;
	
	/**
	 * 玩家Id
	 */
	private Long gameUserId;
	
	/**
	 * 用户im账号
	 */
	private String imGameUserName;
	
	/**
	 * 玩家gameId
	 */
	private String gameUserGameId;
	
	/**
	 * 用户账号所属app
	 */
	private String gameUserAppId;
	
	/**
	 * 客服id
	 */
	private Long customId;
	
	/**
	 * 渠道id
	 */
	private Long channelId;
	
	/**
	 * 咨询类型(1-充值 2-亲友圈 3-推广员 4-活动 100-其他)
	 */
	private Integer problemType;
	
	/**
	 * 问题描述
	 */
	private String problemDesc;
	
	/**
	 * 是否激活
	 */
	private Boolean isActive;
	
	/**
	 * 是否超时警告
	 */
	private Boolean isWarning;
	
	/**
	 * 是否超时自动关闭
	 */
	private Boolean isAutoClose;
	
	/**
	 * 是否回访
	 */
	private Boolean isCallBack;
	
	/**
	 * 客服im账号
	 */
	private String customImUserName;
	
	/**
	 * 客服所属appid
	 */
	private String customAppId;
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	/**
	 * 会话激活时间
	 */
	private Date activeTime;
	
	/**
	 * 响应时间
	 */
	private Date responseTime;
	
	/**
	 * 客服发送次数
	 */
	private Long customSendCount;
	
	/**
	 * 玩家发送次数
	 */
	private Long gameUserSendCount;
	
	/**
	 * 状态(1-待处理 2-处理中 3-已结束)
	 * @see ConversationStatusEnum
	 */
	private Integer status;
	
	public String getCustomImUserName() {
		return customImUserName;
	}

	public void setCustomImUserName(String customImUserName) {
		this.customImUserName = customImUserName;
	}

	public String getCustomAppId() {
		return customAppId;
	}

	public void setCustomAppId(String customAppId) {
		this.customAppId = customAppId;
	}

	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getConversationId() {
		return conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getGameUserId() {
		return gameUserId;
	}

	public void setGameUserId(Long gameUserId) {
		this.gameUserId = gameUserId;
	}

	public String getImGameUserName() {
		return imGameUserName;
	}

	public void setImGameUserName(String imGameUserName) {
		this.imGameUserName = imGameUserName;
	}

	public String getGameUserAppId() {
		return gameUserAppId;
	}

	public void setGameUserAppId(String gameUserAppId) {
		this.gameUserAppId = gameUserAppId;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Integer getProblemType() {
		return problemType;
	}

	public void setProblemType(Integer problemType) {
		this.problemType = problemType;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getCustomSendCount() {
		return customSendCount;
	}

	public void setCustomSendCount(Long customSendCount) {
		this.customSendCount = customSendCount;
	}

	public Long getGameUserSendCount() {
		return gameUserSendCount;
	}

	public void setGameUserSendCount(Long gameUserSendCount) {
		this.gameUserSendCount = gameUserSendCount;
	}

	public Boolean getIsWarning() {
		return isWarning;
	}

	public void setIsWarning(Boolean isWarning) {
		this.isWarning = isWarning;
	}

	public Boolean getIsAutoClose() {
		return isAutoClose;
	}

	public void setIsAutoClose(Boolean isAutoClose) {
		this.isAutoClose = isAutoClose;
	}

	public String getGameUserGameId() {
		return gameUserGameId;
	}

	public void setGameUserGameId(String gameUserGameId) {
		this.gameUserGameId = gameUserGameId;
	}

	public Boolean getIsCallBack() {
		return isCallBack;
	}

	public void setIsCallBack(Boolean isCallBack) {
		this.isCallBack = isCallBack;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	
	
	
}
