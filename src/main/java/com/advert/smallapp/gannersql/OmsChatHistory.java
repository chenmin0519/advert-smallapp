package com.advert.smallapp.gannersql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsChatHistory extends BaseModel{
	
	private static final long serialVersionUID = 7682024027108434040L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long chatId;
	
	/**
	 * 会话id(客服历史记录id)
	 */
	private Long conversationId;
	
	/**
	 * 渠道id
	 */
	private Long channelId;
	
	/**
	 * im消息id
	 */
	private Long msgId;
	
	/**
	 * 发送者id(客服userId)
	 */
	private Long sendId;
	
	/**
	 * 发送者id
	 */
	private Long fromId;
	
	/**
	 * 发送者im账号
	 */
	private String fromImUser;
	
	/**
	 * 发送者appId
	 */
	private String fromAppId;
	
	/**
	 * 发送者类型(1-玩家 2-客服)
	 * @see ChatfromTypeEnum
	 */
	private Integer fromType;
	
	/**
	 * 接收者id
	 */
	private Long targetId;
	
	/**
	 * 接收者im账号
	 */
	private String targetImUser;
	
	/**
	 * 目标appId
	 */
	private String targetAppId;
	
	/**
	 * 接受者类型(1-玩家 2-客服)
	 * @see ChattargetTypeEnum
	 */
	private Integer targetType;
	
	/**
	 * 内容
	 * @see ChatContentVo
	 */
	private String content;
	
	/**
	 * 类型(1-文本消息 2-图片消息 3-语音消息)
	 * @see ChatContentTypeEnum
	 */
	private Integer contentType;
	
	/**
	 * 是否已读
	 */
	private Boolean canRead;

	/**
	 * 关联客服Id
	 */
	private Long refCustomId;
	
	/**
	 * 关联用户Id
	 */
	private Long refGameUserId;
	
	/**
	 * 消息发送时间
	 */
	private Long msgCtime;
	
	/**
	 * 状态 0-正常 1-禁用
	 */
	private Integer status;
	
	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public Boolean getCanRead() {
		return canRead;
	}

	public void setCanRead(Boolean canRead) {
		this.canRead = canRead;
	}

	public Long getConversationId() {
		return conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}

	public Long getRefCustomId() {
		return refCustomId;
	}

	public void setRefCustomId(Long refCustomId) {
		this.refCustomId = refCustomId;
	}

	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	public String getFromImUser() {
		return fromImUser;
	}

	public void setFromImUser(String fromImUser) {
		this.fromImUser = fromImUser;
	}

	public String getFromAppId() {
		return fromAppId;
	}

	public void setFromAppId(String fromAppId) {
		this.fromAppId = fromAppId;
	}

	public Integer getFromType() {
		return fromType;
	}

	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getTargetImUser() {
		return targetImUser;
	}

	public void setTargetImUser(String targetImUser) {
		this.targetImUser = targetImUser;
	}

	public String getTargetAppId() {
		return targetAppId;
	}

	public void setTargetAppId(String targetAppId) {
		this.targetAppId = targetAppId;
	}

	public Integer getTargetType() {
		return targetType;
	}

	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}

	public Long getMsgCtime() {
		return msgCtime;
	}

	public void setMsgCtime(Long msgCtime) {
		this.msgCtime = msgCtime;
	}

	public Long getSendId() {
		return sendId;
	}

	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}

	public Long getRefGameUserId() {
		return refGameUserId;
	}

	public void setRefGameUserId(Long refGameUserId) {
		this.refGameUserId = refGameUserId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	
}
