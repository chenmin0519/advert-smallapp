package com.advert.smallapp.gannersql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OmsCustomProblem extends BaseModel{
	
	private static final long serialVersionUID = -3296939368979512596L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	
	/**
	 * 类型(1-问题 2-答案)
	 */
	private Integer type;
	
	/**
	 * 问题类型
	 */
	private Integer problemType;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 上级id
	 */
	private Long parentId;
	
	/**
	 * 点击次数
	 */
	private Long clickNum;
	
	/**
	 * 有效次数
	 */
	private Long effectiveNum;
	
	/**
	 * 无效次数
	 */
	private Long invalidNum;
	
	/**
	 * 排序
	 */
	private Long sort;
	
	/**
	 * 状态(0-禁用 1-启用)
	 */
	private Integer status;
	
	/**
	 * 是否删除
	 */
	private Boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getProblemType() {
		return problemType;
	}

	public void setProblemType(Integer problemType) {
		this.problemType = problemType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getClickNum() {
		return clickNum;
	}

	public void setClickNum(Long clickNum) {
		this.clickNum = clickNum;
	}

	public Long getEffectiveNum() {
		return effectiveNum;
	}

	public void setEffectiveNum(Long effectiveNum) {
		this.effectiveNum = effectiveNum;
	}

	public Long getInvalidNum() {
		return invalidNum;
	}

	public void setInvalidNum(Long invalidNum) {
		this.invalidNum = invalidNum;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
