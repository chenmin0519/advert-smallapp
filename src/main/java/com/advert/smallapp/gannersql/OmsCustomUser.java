package com.advert.smallapp.gannersql;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsCustomUser extends BaseModel{
	
	private static final long serialVersionUID = -8086459456809832861L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long customId;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 
	 */
	private String imUserName;
	
	/**
	 * im密码
	 */
	private String imPassword;
	
	/**
	 * 渠道id
	 */
	private Long channelId;
	
	/**
	 * 所属appid
	 */
	private String appId;
	
	/**
	 * 客服名称
	 */
	private String name;
	
	/**
	 * 客服昵称
	 */
	private String nickName;
	
	/**
	 * 头像
	 */
	private String headImg;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 状态(0-禁用 1-启用)
	 */
	private Integer status;
	
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	
	/**
	 * 是否在线
	 */
	private Boolean online;
	
	/**
	 * 优先级
	 */
	private Integer sort;

	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	
}
